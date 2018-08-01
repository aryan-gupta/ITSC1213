

public class Game21 {	
	public static void main(String[] str) {
		while (!playGame());
	}
	
	private static Deck[] decks;
	
	public static boolean playGame() {
		decks = new Deck[2];
		for (int ii = 0; ii < decks.length; ++ii) {
			decks[ii] = new Deck();
			decks[ii].reset();
		}
		
		Deck inUseDeck = decks[0];
	public static int getPoints(Vector<Card> hand) {
		// go through our hand and add up all the card values, but skip all the aces
		// we will count all the ace values at the end. We will keep a count of how
		// man aces we have
		int points = 0;
		int numAce = 0;
		for (Card c : hand) {
			int pValue = c.getValue();
			if (pValue == 0) ++numAce;
			points += pValue;
		}
		
		// Now that we have a precount of our points, add up the aces as 11s first, if 
		// we exceed 21 then resort back to the value being 1. However if we exceed 21
		// even if we use some aces as 11 and the rest as 1's then trash eveything and
		// add all of them as 1. This algorithm should be fit for this specific task
		// and doesnt really work with larger values above 21. Im really sleepy rn. Might
		// optimize this later. OMG this looks so ugly
		int aceAdded = 0;
		int tempPoints = points;
		while (aceAdded++ < numAce) {
			if (tempPoints + 11 > 21) {
				tempPoints += 1;
				if (tempPoints > 21) {
					return points + numAce;
				}
			} else {
				tempPoints += 11;
			}
		}
		return tempPoints;
	}
}