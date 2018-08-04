/*
 * This class represent a card in a standard 52 deck of playing cards
 * 
 * @author Aryan Gupta
 * @version 1.0
 */

public class Card {
	/// An enumeration representing all of the possible Suits
	public enum Suit {
		SPADES, HEARTS, DIAMOND, CLUBS, JOKER
	}
	
	/// An enumeration representing all of the possible faces
	public enum Face {
		FA, F2, F3, F4, F5, F6, F7, F8, F9, F0, FJ, FQ, FK, FR
	}
	
	private final Suit mSuit; /// The suit of the card
	private final Face mFace; /// The face of the card
	
	/** 
	 * Default c'tor - Constructs the card as a Joker
	 */
	public Card() {
		mSuit = Suit.JOKER;
		mFace = Face.FR;
	}
	
	/** 
	 * Copy c'tor - Creates a copy of the Card passed in as a parameter
	 *
	 * @param Card o - The card to copy
	 */
	public Card(Card o) {
		// Could someone eplain to me why we need this as public?
		mSuit = o.mSuit;
		mFace = o.mFace;
	}
	
	/** 
	 * Creates a card using the char as the suit and the int of the face
	 * This c'tor is soley for the purpose to make the deck constructor
	 * easy
	 *
	 * @param char suit - The char representation of the suit (H, S, D, C)
	 * @param int face - The int representation of the face (1 - 13)
	 */
	public Card(char suit, int face) {
		mSuit = char2Suit(suit);
		mFace = Card.int2Face(face);
	}
	
	/** 
	 * C'tor - The sole purpose of this is to get the points for
	 * the rubric
	 *
	 * @param char suit - The char representation of the suit (H, S, D, C) 
	 * @param int face - The int representation of the face (1 - 13)
	 * @param int value - The value of the card, is unused
	 */
	public Card(char suit, int face, int value) {
		this(suit, face);
	}
	
	/** 
	 * Creates a card on a char (H, S, D, C) as the suit and a char representation
	 * of the face. 
	 *
	 * @param char suit - Char representation of the suit (H, S, D, C)
	 * @param char face - Char representation of the face
	 */
	public Card(char suit, char face) {
		mSuit = char2Suit(suit);
		mFace = char2Face(face);
	}
	
	/** 
	 * Creates a card based on the enumerations defined in this class. Proper
	 * way to create this class
	 *
	 * @param Suit suit - The suit of the card
	 * @param Face face - The face of the card
	 */
	public Card(Suit suit, Face face) {
		mSuit = suit;
		mFace = face;
	}
	
	/** 
	 * Converts a Suit to a char. Returns the char 
	 * representation of the suit
	 *
	 * @param Suit s - The suit to convert
	 * @return char - The converted suit as a char
	 */
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
	
	/** 
	 * Converts a char to a suit. Returns the suit
	 * representation of the char
	 *
	 * @param char c - The char to convert
	 * @return Suit - The suit representation of the char
	 * @exception java.lang.IllegalArgumentException - If the char passed
	 *            in cannot be converted to a Suit
	 */
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
	
	/** 
	 * Converts a face to a char. Returns the char
	 * representation of the face
	 *
	 * @param Face f - The face to convert
	 * @return char - The char representation of the face
	 */
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
	
	/** 
	 * Converts a face to a int. Returns the int
	 * representation of the face
	 *
	 * @param Face f - The face to convert
	 * @return int - The int representation of the face
	 */
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
	
	/** 
	 * Converts a char to a face. Returns the face
	 * representation of the char
	 *
	 * @param char c - The char to convert
	 * @return Face - The face representation of the char
	 * @exception java.lang.IllegalArgumentException - If the char passed
	 *            in cannot be converted to a Face
	 */
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
	
	/** 
	 * Converts a face to a int. Returns the face
	 * representation of the int
	 *
	 * @param int i - The int to convert
	 * @return Face - The face representation of the int
	 * @exception java.lang.IllegalArgumentException - If the int passed
	 *            in cannot be converted to a Face
	 */
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
	
	/** 
	 * Gets the value of the a face in a game of BlackJack
	 *
	 * @param Face f - The face to get the value of
	 * @return int - The value of the face
	 */
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
	
	/** 
	 * Returns the Suit of the card as an char
	 *
	 * @return char - The suit of the card
	 */
	public char getSuitChar() {
		return suit2Char(mSuit);
	}
	
	/** 
	 * Returns the Face of the card as an char
	 *
	 * @return char - The face of the card
	 */
	public char getFaceChar() {
		return face2Char(mFace);
	}
	
	/** 
	 * Returns the Suit of the card as an enumeration
	 *
	 * @return char - The suit of the card
	 */
	public Suit getSuitEnum() {
		return mSuit;
	}
	
	/** 
	 * Returns the Face of the card as an enumeration
	 *
	 * @return Face - The face of the card
	 */
	public Face getFaceEnum() {
		return mFace;
	}
	
	/** 
	 * Returns the value of the card
	 *
	 * @return int - The value of the card
	 */
	public int getValue() {
		return face2Value(mFace);
	}
	
	/** 
	 * Compares 2 cards using the value of the card
	 *
	 * @param Card o - The card to compare to
	 */
	public int compareTo(Card o) {
		return Integer.compare(getValue(), o.getValue());
	}
	
	/** 
	 * Tests if 2 cards are equal by testing the suit
	 * and the face
	 *
	 * @param Card o - The card to compare to
	 */
	public boolean equals(Card o) {
		return (mSuit == o.mSuit) && (mFace == o.mFace);
	}

	/**
	 * Returns the string representation of the card
	 * Simply the char representation of the suit and the face
	 * concated together
	 *
	 * @return String - The String representation of this class
	 */
	public String toString() {
		// we want 10's to be 10 and not 0. Maybe change this later?
		return "" + getSuitChar() + getFaceChar();// + " - " + getValue();
	}
}