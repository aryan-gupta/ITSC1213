
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
		return '\0';
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