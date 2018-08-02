
import java.util.Vector;
import java.util.Scanner;

public class Game21 {
	private static Deck[] decks;
	private static int   inUseDeck;
	private static int playerWin;
	private static int dealerWin;
	
	static {
		// There was 2 decks however professor said that 
		// we only need 1 deck. Didnt want to edit too many
		// codes, however this should work. 
		decks = new Deck[1];
		for (int ii = 0; ii < decks.length; ++ii) {
			decks[ii] = new Deck();
			decks[ii].reset();
		}
		
		inUseDeck = 0;
		playerWin = 0;
		dealerWin = 0;
	}
	
	public static void main(String[] str) {
		while (playGame());
		
		System.out.println("Player Wins: " + playerWin);
		System.out.println("Dealer Wins: " + dealerWin);
		
		// output to a file:
	}
	
	private static Card getCard() {
		Card c = null;
		try {
			c = decks[inUseDeck].getTopCard();
		} catch (Deck.CardDrawOnEmptyDeck e) {
			decks[inUseDeck].reset();
			inUseDeck = (inUseDeck + 1) % 1;
			c = getCard(); // it will work, stop judging
		}
		return c;
	}
	
	private static boolean playGame() {	
		// array to store the cards in our hands
		Vector<Card> dealerCards = new Vector<>();
		Vector<Card> playerCards = new Vector<>();
		
		// By default, each person gets 2 cards
		dealerCards.add(getCard());
		dealerCards.add(getCard());
		
		playerCards.add(getCard());
		playerCards.add(getCard());
		
		renderHand(dealerCards, playerCards, false);
		
		Scanner in = new Scanner(System.in);
		
		boolean playerBust = false;
		do {
			int choice = -1;
			do {
				System.out.println("What would you like to do: ");
				System.out.println("0) Quit");
				System.out.println("1) Hit");
				System.out.println("2) Stand");
				System.out.print(":: ");
				
				try {
					choice = in.nextInt();
				} catch (java.util.InputMismatchException e) {
					System.out.println("Please enter an integer...");
					in.next(); // the wrong value is still in the buffer
					choice = -1;
				}
			} while (choice < 0);
			
			
			switch (choice) {
				case 0: {
					System.out.print("Would you like to play again?:: ");
					String str = in.next();
					if (str.charAt(0) == 'y')
					//if (in.nextLine().charAt(0) == 'y')
						return true;
					return false;
				}
				
				case 1: {
					playerCards.add(getCard());
					if (getPoints(playerCards) > 21) playerBust = true;
				} break;
				
				case 2: {
					if (getPoints(dealerCards) < getPoints(playerCards)) {
						while (getPoints(dealerCards) < 17) {
							dealerCards.add(getCard());
						}
					}
				} break;				
			}
			
			if (choice == 2 || playerBust) {
				renderHand(dealerCards, playerCards, true);
				
				int dealerPoints = getPoints(dealerCards);
				int playerPoints = getPoints(playerCards);
				
				if        (playerPoints > 21) {
					System.out.println("............YOU LOSE............");
					++dealerWin;
				} else if (dealerPoints > 21) {
					System.out.println("............YOU WIN.............");
					++playerWin;
				} else if (dealerPoints > playerPoints) {
					System.out.println("............YOU LOSE............");
					++dealerWin;
				} else if (dealerPoints < playerPoints) {
					System.out.println("............YOU WIN.............");
					++playerWin;
				} else { // Ties no one wins
					System.out.println("............YOU TIE.............");
				}
				
				System.out.print("Would you like to play again?:: ");
				String str = in.next();
				if (str.charAt(0) == 'y')
				//if (in.nextLine().charAt(0) == 'y')
					return true;
				return false;
			} else {
				renderHand(dealerCards, playerCards, false);
			}				
		} while (!playerBust);
		return false;
	}
	
	private static void renderHand(Vector<Card> dealer, Vector<Card> player, boolean showDealer) {
		System.out.print("Dealer: ");
		if (showDealer) {
			for (Card c : dealer) {
				System.out.print(c);
				System.out.print(" ");
			}
			
			System.out.println();
			
			System.out.println("Dealer Points: " + getPoints(dealer));
		} else {
			System.out.print(dealer.firstElement());
			System.out.print(" ");
			
			for (int ii = 1; ii < dealer.size(); ++ii) {
				System.out.print("-- ");
			}
			
			System.out.println();
			
			int dealerPoints = dealer.firstElement().getValue();
			if (dealerPoints == 0) dealerPoints = 1;
			
			System.out.println("Dealer Points: " + dealerPoints);
		}
		
		System.out.print("Player: ");
		for (Card c : player) {
			System.out.print(c);
			System.out.print(" ");
		}
		
		System.out.println();
		
		System.out.println("Your Points: " + getPoints(player));
	}
	
	private static int getPoints(Vector<Card> hand) {
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
		
		if (numAce == 0)
			return points;
		
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