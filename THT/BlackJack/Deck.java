
/** 
 * Represents a standard 52-card deck
 *
 * @author Aryan Gupta
 * @version 1.0
 */
import java.util.Arrays;
import java.util.Collections;

public class Deck {
	// An exception thrown when the deck is empty
	public class CardDrawOnEmptyDeck extends java.lang.Exception {  }
	
	private static final int SIZE; /// Size of a deck
	private Card[] mDeck; /// The cards in this deck
	private int mTopCard; /// Index of the top card on the deck
	
	static {
		// a typical deck has 52 cards
		SIZE = 52;
	}
	
	/**
	 * The default c'tor - Creates a deck of 52 cards
	 */
	public Deck() {
		mDeck = new Card[SIZE];
		int idx = 0;
		// Java doesnt have initializer lists or iterators but I forgive it because 
		// it is letting me do this abomination. Thank god for GC
		for (char suit : new char[] {'C', 'D', 'H', 'S'}) {
			// Iterate from Ace to King
			for (int val = Card.face2Int(Card.Face.FA); val <= Card.face2Int(Card.Face.FK); ++val) {
				try {
					mDeck[idx++] = new Card(suit, val);
				} catch (java.lang.IllegalArgumentException e) {
					System.out.println("Error: Well... you definitely broke something");
				}
			}
		}
		mTopCard = SIZE;
	}
	
	/**
	 * Returns the top card on the deck and decrements the size of the 
	 * available deck
	 *
	 * @exception CardDrawOnEmptyDeck if the deck is empty
	 * @return Card - The top file on the deck
	 */
	public Card getTopCard() throws CardDrawOnEmptyDeck {
		// according to the professor, must use a copy c'tor
		if (mTopCard <= 0) throw new CardDrawOnEmptyDeck();
		return new Card(mDeck[--mTopCard]);
	}
	
	/**
	 * Resets the deck. Similar to restoring all the cards and shuffling
	 * the deck
	 */
	public void reset() {
		mTopCard = SIZE;
		shuffle();
	}
	
	/**
	 * Shuffles the deck
	 */
	public void shuffle() {
		// why reinvent the wheel?
		// Collections.shuffle(Arrays.asList(mDeck));
		
		// for the rubric points
		java.util.Random rand = new java.util.Random();
		int loop = 1000;
		while (loop --> 0) {
			int a = rand.nextInt(SIZE);
			int b = rand.nextInt(SIZE);
			
			// swap
			Card tmp = mDeck[a];
			mDeck[a] = mDeck[b];
			mDeck[b] = tmp;
		}
	}
	
	/**
	 * Returns the string representation of the deck
	 * Simply a comma separated value of the cards
	 *
	 * @return String - The String representation of this class
	 */
	public String toString() {
		StringBuilder build = new StringBuilder();
		
		for (Card c : mDeck) {
			if (c == null) continue;
			build.append(c.toString() + ", ");
		}
		
		String str = build.toString();
		
		// remove the last 2 chars (tailing comma and space)
		return str.substring(0, str.length() - 2);
	}
}