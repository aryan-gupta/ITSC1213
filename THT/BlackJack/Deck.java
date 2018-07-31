
import java.util.Arrays;
import java.util.Collections;

public class Deck {
	public class CardDrawOnEmptyDeck extends java.lang.Exception {  }
	
	private static final int SIZE;
	private Card[] mDeck;
	private int mTopCard;
	
	static {
		SIZE = 52;
	}
	
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
	
	public Card getTopCard() throws CardDrawOnEmptyDeck {
		if (mTopCard < 0) throw new CardDrawOnEmptyDeck();
		return mDeck[--mTopCard];
	}
	
	public void reset() {
		mTopCard = SIZE;
		shuffle();
	}
	
	public void shuffle() {
		// why reinvent the wheel?
		Collections.shuffle(Arrays.asList(mDeck));
	}
	
	public String toString() {
		StringBuilder build = new StringBuilder();
		
		for (Card c : mDeck) {
			if (c == null) continue;
			build.append(c.toString() + ", ");
		}
		
		String str = build.toString();
		
		return str.substring(0, str.length() - 2);
	}
}