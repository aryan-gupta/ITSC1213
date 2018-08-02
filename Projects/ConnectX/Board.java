
import java.util.Arrays;

public class Board {
	private char mBoard[][];
	private int mHeight[];
	private int mDepth;
	
	private Board() {}
	
	public Board(final Board o) {
		mDepth = o.mDepth;
		mBoard = new char[o.mBoard.length][o.mBoard[0].length];
		for (int row = 0; row < o.mBoard.length; ++row) {
			for (int col = 0; col < o.mBoard[0].length; ++col) {
				mBoard[row][col] = o.mBoard[row][col];
			}
		}
		
		// https://stackoverflow.com/questions/3329163/is-there-an-equivalent-to-memcpy-in-java
		mHeight = Arrays.copyOf(mHeight, mHeight.length);
	}
	
	public Board(int depth, int row, int col) {
		mDepth = depth;
		mBoard = new char[row][col];
		mHeight = new int[col];
		while (col --> 0) mHeight[col] = row - 1;
	}
	
	public char get(Coordinate c) {
		return mBoard[c.getR()][c.getC()];
	}
	
	public boolean place(char ch, int col) {
		int row = getTopIndex(col);
		if (row == -1) return false;
		set(ch, new Coordinate(Coordinate.CoordType.RC, row, col));
		incTopIndex(col);
		return true;
	}
	
	private int getTopIndex(int col) {
		int top = mHeight[col];
		return top;
	}
	
	private void incTopIndex(int col) {
		--mHeight[col];
	}
	
	private void set(char ch, Coordinate c) {
		mBoard[c.getR()][c.getC()] = ch;
	}
	
	public char getWinner() {
		// sigh... https://stackoverflow.com/questions/9963325/shortening-type-names-in-java
		
		// Pretty much we want to go through each position on the board and skip any ones that
		// don't have any chars (char at that position is \0). For the Up/Down direction we only
		// need to check every mDepth row starting at row number -1. So for example, if our mDepth
		// is 4 then we check row 3, 7, 11 and so on. We can get this by moding it with mDepth and
		// checking if its equal to mDepth - 1 (% 4 would equal to 4, 8, 12). Similarly, for the 
		// left right direction we want to do the same thing with the columns instead of the rows. 
		// The diagonals is interesting, we want to check a corner row/column starting at (3, 3),
		// (7, 7), (11, 11) and so on. So like this:
		//   0 1 2 3 4 5 6 7 8 9
		// 0
		// 1
		// 2
		// 3       X X X X X X X
		// 4       X
		// 5       X
		// 6       X
		// 7       X       X X X
		// 8       X       X
		// 9       X       X
		// This is only valid for top left to bottom right. Where ever there is an X we want to 
		// check in the tl to br direction. For the other diagonal direction we have this:
		//   0 1 2 3 4 5 6 7 8 9
		// 0
		// 1
		// 2
		// 3 X X X X X X X X
		// 4               X
		// 5               X
		// 6 X X X X       X
		// 7       X       X
		// 8       X       X
		// 9       X       X
		// Lets go back to the up/down direction:
		//   0 1 2 3 4 5 6 7 8 9
		// 0
		// 1
		// 2
		// 3 X X X X X X X X X X
		// 4
		// 5
		// 6 X X X X X X X X X X
		// 7
		// 8
		// 9
		// And the left/right direction:
		//   0 1 2 3 4 5 6 7 8 9
		// 0       X       X
		// 1       X       X
		// 2       X       X
		// 3       X       X
		// 4       X       X
		// 5       X       X
		// 6       X       X
		// 7       X       X
		// 8       X       X
		// 9       X       X
		// If we overlay them over we get this pattern:
		//   0 1 2 3 4 5 6 7 8 9
		// 0       X       X
		// 1       X       X
		// 2       X       X
		// 3 X X X X X X X X X X
		// 4       X O     X
		// 5       X   O   X
		// 6       X     O X
		// 7 X X X X X X X X X X
		// 8       X       X
		// 9       X       X
		// To show diagonal distance I have added a few Os's
		// Honestly, I'm not sure how to implement this with a for loop, so I'm just
		// going to loop though every one of them and just filter the ones we want
		// to use. 
		for (int row = mBoard.length - 1; row >= 0; --row) {
			for (int col = 0; col < mBoard[0].length; ++col) {
				Coordinate c = new Coordinate(Coordinate.CoordType.RC, row, col);
				char ch = get(c);
				if (ch == 0) continue;
				
				// UP DOWN DIRECTION
				if (row % mDepth == mDepth - 1) 
					if ((getInRowLength(ch, 0, 0, 1, c) + getInRowLength(ch, 0, 0, -1, c)) > mDepth)
						return ch;
				
				// LEFT RIGHT DIRECTION
				if (col % mDepth == mDepth - 1)
					if ((getInRowLength(ch, 0, 1, 0, c) + getInRowLength(ch, 0, -1, 0, c)) > mDepth)
						return ch;
				
				// DIAGONAL DIRECTION
				if (row % mDepth == mDepth - 1 || col % mDepth == mDepth - 1) {
					if ((getInRowLength(ch, 0, -1, 1, c) + getInRowLength(ch, 0, 1, -1, c)) > mDepth)
						return ch;
					
					if ((getInRowLength(ch, 0, -1, -1, c) + getInRowLength(ch, 0, 1, 1, c)) > mDepth)
						return ch;
				}
			}
		}
		return 0; // return \0 on no winner
	}
	
	/**
	 * Recursive algorithm to get the number of chips 'in-row' of each other. 
	 * 
	 */
	private int getInRowLength(char ch, int depth, int dx, int dy, Coordinate loc) {
		// we are given a direction by \p dx and \p dy. The algorithm travels that direction
		// until it reaches a char that is not equal to \p ch. Each call of this function
		// is the next place in the direction of dx/dy, depth is incremented receptively
		// This function should be run in every 8 cardinal direction (n s e w ne nw se sw)		
	
		// If we are out of bounds then just return the depth
		if (checkOutOfBounds(loc))
			return depth;
		
		// if we have the same char as the 0th depth then continue deeper
		// else just return the depth because this depth is the last one in
		// a sequence
		if (get(loc) == ch)
			return getInRowLength(ch, depth + 1, dx, dy, loc.add(Coordinate.CoordType.XY, dx, dy));
		return depth;
	}
	
	private boolean checkOutOfBounds(Coordinate c) {
		return
		   c.getR() >= mBoard   .length
		|| c.getC() >= mBoard[0].length
		|| c.getR() <  0
		|| c.getC() <  0;
		
	}
	
	public String toString() {
		String str = new String();
		
		for (char[] array : mBoard) {
			for (char c : array) {
				str += (c == 0)? ' ': Character.toString(c);
				str += " ";
			}
			str += "\n";
		}
		
		return str;
	}
	
}