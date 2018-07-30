
import java.util.Arrays;
import java.util.Collections;

public class Deck {
	private Card[] mDeck;
	private int mTopCard;
	
	public Deck() throws java.lang.IllegalArgumentException {
		mDeck = new Card[52];
		int idx = 0;
		// Java doesnt have initializer lists or iterators but I forgive it because 
		// it is letting me do this abomination. Thank god for GC
		for (char suit : new char[] {'C', 'D', 'H', 'S'}) {
			// Iterate from Ace to King
			for (int val = Card.face2Int(Card.Face.FA); val <= Card.face2Int(Card.Face.FK); ++val) {
				mDeck[idx++] = new Card(suit, val);
			}
		}
		mTopCard = 51;
	}
	
	public Card getTopCard() {
		return mDeck[mTopCard];
	}
	
	public void shuffle() {
		// why reinvent the wheel?
		Collections.shuffle(Arrays.asList(mDeck));
	}
	
	public String toString() {
		StringBuilder build = new StringBuilder();
		
		for (Card c : mDeck) {
			if (c == null) continue;
			build.append(c.toString() + "\n");
		}
		
		return build.toString();
	}
}