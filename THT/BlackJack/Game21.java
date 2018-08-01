
import java.util.Vector;

public class Game21 {	
	public static void main(String[] str) {
		while (playGame());
	}
	
	private static Deck[] decks;
	private static Deck   inUseDeck;
	
	public static boolean playGame() {
		decks = new Deck[2];
		for (int ii = 0; ii < decks.length; ++ii) {
			decks[ii] = new Deck();
			decks[ii].reset();
		}
		
		inUseDeck = decks[0];
		
		// array to store the cards in our hands
		Vector<Card> dealerCards = new Vector<>();
		Vector<Card> playerCards = new Vector<>();
		
		// By default, each person gets 2 cards
		try {
			dealerCards.add(inUseDeck.getTopCard());
			dealerCards.add(inUseDeck.getTopCard());
			
			// playerCards.add(inUseDeck.getTopCard());
			// playerCards.add(inUseDeck.getTopCard());
			// playerCards.add(inUseDeck.getTopCard());
			// playerCards.add(inUseDeck.getTopCard());
		} catch (Deck.CardDrawOnEmptyDeck e) {
			System.err.println("Error: Deck does not contain 4 cards at initial dealing of cards");
			System.exit(-1);
		}
		
		playerCards.add(new Card('C', 'A'));
		playerCards.add(new Card('C', 'A'));
		playerCards.add(new Card('C', '2'));
		
		renderHand(dealerCards, playerCards);
		
		return false;
	}
	
	private static void renderHand(Vector<Card> dealer, Vector<Card> player) {
		System.out.print("Dealer: ");
		System.out.print(dealer.firstElement());
		System.out.print(" ");
		
		for (int ii = 1; ii < dealer.size(); ++ii) {
			System.out.print("-- ");
		}
		
		System.out.println();
		
		System.out.print("Player: ");
		for (Card c : player) {
			System.out.print(c);
			System.out.print(" ");
		}
		
		System.out.println();
		
		System.out.println("Total Points: " + getPoints(player));
	}
	
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
		
		// Now that we have a precount of our points, if we have one ace then count it as 
		// a 11 but if we exceed 21 count it as 1. If we have more than one ace as 11 
		// if we can (final sum must be under 21) then count the rest as 1.
		
		if (numAce == 1) {
			if (points + 11 > 21) {
				return points + 1;
			}
			return points + 11;
		}
		
		// try to get points with one ace as 11 (points + 11 + (numAce - 1))
		if (points + 10 + numAce > 21) {
			return points + numAce;
		}
		
		return points + 10 + numAce;
	}
}