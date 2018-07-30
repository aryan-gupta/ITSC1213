/*
 * This class represent a card in a standard 52 deck of playing cards
 * The 
 */

public class Card {
	public enum Suit {
		SPADES, HEARTS, DIAMOND, CLUBS, JOKER
	}
	
	public enum Face {
		FA, F2, F3, F4, F5, F6, F7, F8, F9, F0, FJ, FQ, FK, FR
	}
	
	private final Suit mSuit;
	private final Face mFace;
	
	public Card() {
		// Could someone eplain to me why we need this as public?
		mSuit = Suit.JOKER;
		mFace = Face.FR;
	}
	
	public Card(char suit, int face) {
		mSuit = char2Suit(suit);
		mFace = Card.int2Face(face);
	}
	
	public Card(char suit, char face) {
		mSuit = char2Suit(suit);
		mFace = char2Face(face);
	}
	
	public Card(Suit suit, Face face) {
		mSuit = suit;
		mFace = face;
	}
	
	public static char suit2Char(Suit s) {
		switch (s) {
			case SPADES : return 'S';
			case HEARTS : return 'H';
			case DIAMOND: return 'D';
			case CLUBS  : return 'C';
			case JOKER  : return 'R';
		}
		return 0;
	}
	
	public static Suit char2Suit(char c) throws java.lang.IllegalArgumentException {
		switch (c) {
			case 'S': return Suit.SPADES;
			case 'H': return Suit.HEARTS;
			case 'D': return Suit.DIAMOND;
			case 'C': return Suit.CLUBS;
			case 'K': return Suit.JOKER;
			
			default: throw new java.lang.IllegalArgumentException();
		}
	}
	
	public static char face2Char(Face f) {
		switch (f) {
			case FA: return 'A';
			case F2: return '2';
			case F3: return '3';
			case F4: return '4';
			case F5: return '5';
			case F6: return '6';
			case F7: return '7';
			case F8: return '8';
			case F9: return '9';
			case F0: return '0';
			case FJ: return 'J';
			case FQ: return 'Q';
			case FK: return 'K';
		}
		return 0;
	}
	
	public static int face2Int(Face f) {
		switch (f) {
			case FA: return  1;
			case F2: return  2;
			case F3: return  3;
			case F4: return  4;
			case F5: return  5;
			case F6: return  6;
			case F7: return  7;
			case F8: return  8;
			case F9: return  9;
			case F0: return  9;
			case FJ: return 11;
			case FQ: return 12;
			case FK: return 13;
		}
		return -1;
	}
	
	public static Face char2Face(char c) throws java.lang.IllegalArgumentException {
		switch (c) {
			case 'A': return Face.FA;
			case '2': return Face.F2;
			case '3': return Face.F3;
			case '4': return Face.F4;
			case '5': return Face.F5;
			case '6': return Face.F6;
			case '7': return Face.F7;
			case '8': return Face.F8;
			case '9': return Face.F9;
			case '0': return Face.F0;
			case 'J': return Face.FJ;
			case 'Q': return Face.FQ;
			case 'K': return Face.FK;
			
			default: throw new java.lang.IllegalArgumentException();
		}
	}
	
	public static Face int2Face(int i) throws java.lang.IllegalArgumentException {
		switch (i) {
			case  1: return Face.FA;
			case  2: return Face.F2;
			case  3: return Face.F3;
			case  4: return Face.F4;
			case  5: return Face.F5;
			case  6: return Face.F6;
			case  7: return Face.F7;
			case  8: return Face.F8;
			case  9: return Face.F9;
			case 10: return Face.F0;
			case 11: return Face.FJ;
			case 12: return Face.FQ;
			case 13: return Face.FK;
			
			default: throw new java.lang.IllegalArgumentException();
		}
	}
	
	public static int face2Value(Face f) {
		switch (f) {
			case FA: return  0;
			case F2: return  2;
			case F3: return  3;
			case F4: return  4;
			case F5: return  5;
			case F6: return  6;
			case F7: return  7;
			case F8: return  8;
			case F9: return  9;
			case F0: return 10;
			case FJ: return 10;
			case FQ: return 10;
			case FK: return 10;
		}
		return -1;
	}
	
	public char getSuitChar() {
		return suit2Char(mSuit);
	}
	
	public char getFaceChar() {
		return face2Char(mFace);
	}
	
	public Suit getSuitEnum() {
		return mSuit;
	}
	
	public Face getFaceEnum() {
		return mFace;
	}
	
	public int getValue() {
		return face2Value(mFace);
	}
	
	public int compareTo(Card o) {
		return Integer.compare(face2Int(mFace), face2Int(o.mFace));
	}
	
	public boolean equals(Card o) {
		return (mSuit == o.mSuit) && (mFace == o.mFace);
	}

	public String toString() {
		return "" + getSuitChar() + getFaceChar();// + " - " + getValue();
	}
}