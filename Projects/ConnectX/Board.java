
public class Board {
	private char mBoard[][];
	private int mHeight[];
	private int mDepth;
	
	private Board() {}
	
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
	
	// return \0 on no winner
	public char getWinner() {
		// sigh... https://stackoverflow.com/questions/9963325/shortening-type-names-in-java
		
		for (int row = mBoard.length - 1; row >= 0; --row) {
			for (int col = 0; col < mBoard[0].length; ++col) {
				Coordinate c = new Coordinate(Coordinate.CoordType.RC, row, col);
				char ch = get(c);
				if (ch == 0) continue;
				
				// UP DOWN DIRECTION
				if (row % mDepth == mDepth - 1) 
					if ((getInRowLength(ch, 0,  0,  1, c) + getInRowLength(ch, 0,  0, -1, c) - 1) >= mDepth)
						return ch;
					
				// LEFT RIGHT DIRECTION
				if (col % mDepth == mDepth - 1)
					if ((getInRowLength(ch, 0,  1,  0, c) + getInRowLength(ch, 0, -1,  0, c) - 1) >= mDepth)
						return ch;
				
				// DIAGONAL DIRECTION
				if (row % mDepth == mDepth - 1 || col % mDepth == mDepth - 1) {
					if ((getInRowLength(ch, 0, -1,  1, c) + getInRowLength(ch, 0,  1, -1, c) - 1) >= mDepth)
						return ch;
					
					if ((getInRowLength(ch, 0, -1, -1, c) + getInRowLength(ch, 0,  1,  1, c) - 1) >= mDepth)
						return ch;
				}
			}
		}
		return 0;
		
	}
	
	private int getInRowLength(char ch, int depth, int dx, int dy, Coordinate loc) {
		if (checkOutOfBounds(loc))
			return depth;
		
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