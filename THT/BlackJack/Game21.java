

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
	}
}