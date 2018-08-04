
/** 
 * Plays a game of BlackJack and outputs the result to a file
 *
 * @author Aryan Gupta
 * @version 1.0
 */
import java.util.Vector;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class Game21 {
	private static Deck[] decks; /// Stores all of our deck of cards
	private static int inUseDeck; /// Index of the deck in use 
	private static int playerWin; /// The number of times the player has won
	private static int dealerWin; /// The number of times the dealer has won
	private static Scanner in; /// The scanner for keyboard input
	private static Vector<Card> dealerCards; /// The cards in the dealer's hand
	private static Vector<Card> playerCards; /// The cards in the player's hand
	
	static {
		in = new Scanner(System.in);
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
		
		// array to store the cards in our hands
		dealerCards = new Vector<>();
		playerCards = new Vector<>();
	}
	
	/** 
	 * Program entry point
	 *
	 * @param String[] args - The command-line arguments
	 */
	public static void main(String[] args) {
		while (playGame());
		output();
	}
	
	/**
	 * Outputs the result of the game to a file and to the console
	 */
	private static void output() {
		// We want to try to open the file firstElement
		// if our scanner throws FileNotFoundException
		// then we havent played this game before and we 
		// notify the rest of the function
		Scanner inFile;
		try {
			inFile = new Scanner(new File("data.bj21"));
		} catch (java.io.FileNotFoundException e) {
			System.err.println("File does not exist. Creating file...");
			inFile = null;
		}
		
		// File Format:
		/*
			Magic number
			Total Player Wins: <INT1> Total Dealer Wins: <INT2>
			Game <INT3> :
			Player Wins:<INT4> Dealer Wins<INT5>
			....
		*/
		// Magic number - BJ
		// INT1 - Total number of player wins (sum of all INT4's)
		// INT2 - Total number of dealer wins (sum of all INT5's)
		// INT3 - Game Number (increasing)
		// INT4 - That game's player wins
		// INT5 - That game's dealer wins
		
		// load the file data if it exists
		java.util.Vector<String> fileLines = new java.util.Vector<String>();
		if (inFile != null) {
			while (inFile.hasNext()) {
				fileLines.add(inFile.nextLine());
			}
			
			inFile.close();
			
			// Check for the magic number
			if (fileLines.size() > 0 && !fileLines.elementAt(0).equals("BJ")) {
				System.err.println("File is corrupt. Recreating file...");
				fileLines.clear();
			}		
		}
		
		// OutputFile
		PrintWriter outFile = null;
		try {
			outFile = new PrintWriter("data.bj21");
		} catch (java.io.IOException e) {
			System.err.println("Error #128EDF2100. Don't bother looking it up, the developer didn't document this as well as he should have");
			System.exit(-1);
		}
		
		// output the magic number
		outFile.println("BJ");
		
		// load and update total wins
		int totalPW = playerWin;
		int totalDW = dealerWin;
		if (fileLines.size() > 0) {
			String[] line = fileLines.elementAt(1).split(" ");
			totalPW += Integer.parseInt(line[3]);
			totalDW += Integer.parseInt(line[7]);
		}
		
		outFile.println("Total Player Wins: " + totalPW + " Total Dealer Wins: " + totalDW);
		
		// output the contents of the old file, these stuff don't change
		for (int ii = 2; ii < fileLines.size(); ++ii) {
			outFile.println(fileLines.elementAt(ii));
		}
		
		// get our game number in respect to the file
		int gameNum = 1;
		if (fileLines.size() >= 2)
			gameNum = Integer.parseInt(fileLines.elementAt(fileLines.size() - 2).split(" ")[1]) + 1;
		
		// output the game data
		outFile.println("Game " + gameNum + " :");
		outFile.println("Player Wins: " + playerWin + " Dealer Wins: " + dealerWin);
		
		// flush
		outFile.close();
		
		// output to console 
		System.out.println("Current Run:");
		System.out.println("Player Wins: " + playerWin);
		System.out.println("Dealer Wins: " + dealerWin);
		System.out.println("Total:");
		System.out.println("Player Wins: " + totalPW);
		System.out.println("Dealer Wins: " + totalDW);
	}
	
	/**  
	 * Returns a card from the deck, handles the issue of a deck running out of
	 * cards and switching to a new Deck
	 *
	 * @return Card - A card from the decks
	 */
	private static Card getCard() {
		// get a card, update the inUseDeck to next deck if the deck is empty
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
	
	/** 
	 * Plays a game of BlackJack
	 *
	 * @return boolean - If we want to play again
	 */
	private static boolean playGame() {			
		// By default, each person gets 2 cards
		dealerCards.add(getCard());
		dealerCards.add(getCard());
		playerCards.add(getCard());
		playerCards.add(getCard());
		
		// output our hands
		renderHand(dealerCards, playerCards, false);
		
		boolean quit = false;
		do {
			int choice = getChoice();
			// 0. Quit
			// 1. Hit
			// 2. Stand
			switch (choice) {
				case 0: quit = true; break;				
				case 1: playerCards.add(getCard()); break;
				case 2: doDealerTurn(); break;				
			}
			
			// if we stand or bust
			if (choice == 2 || getPoints(playerCards) > 21) {
				printWinner();
				quit = true;
			} 
			
			if (!quit) renderHand(dealerCards, playerCards, false);	
		} while (!quit);
		return askPlayAgain();
	}
	
	/**
	 * Returns a choice whether the user wants to quit, hit or stand
	 *
	 * @return int - The choice the user choice:
	 *               0 - quit
	 *               1 - hit
	 *               2 - stand
	 */
	private static int getChoice() {
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
		} while (choice < 0 && choice > 2);
		return choice;
	}
	
	/**
	 * Asks the user if they want to play the game again
	 *
	 * @return boolean - If the user wants to play again or not
	 */
	private static boolean askPlayAgain() {
		System.out.print("Would you like to play again?:: ");
		String str = in.next();
		if (str.charAt(0) == 'y')
			return true;
		return false;
	}
	
	/** 
	 * Does the dealer's turn
	 */
	private static void doDealerTurn() {
		// If we have more points then don't draw any cards
		// otherwise draw cards until they have 17 or greater
		if (getPoints(dealerCards) < getPoints(playerCards)) {
			while (getPoints(dealerCards) < 16) {
				dealerCards.add(getCard());
			}
		}
	}
	
	/** 
	 * Prints the final state of the hands and the winner
	 */
	private static void printWinner() {
		// print cards with all the dealer cards
		renderHand(dealerCards, playerCards, true);
		
		// get points of both hands
		int dealerPoints = getPoints(dealerCards);
		int playerPoints = getPoints(playerCards);
		
		// Whoever has more than 21 points loses
		// whoever has the most points (under 21) wins
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
	}
	
	/**
	 * Prints out the hands of the dealer and the player
	 *
	 * @param Vector<Card> dealer - The hand of the dealer
	 * @param Vector<Card> player - The hand of the player
	 * @param boolean showDealer - Should the entire dealer's hand be printed
	 */
	private static void renderHand(Vector<Card> dealer, Vector<Card> player, boolean showDealer) {
		System.out.print("Dealer: ");
		// if we want to show the dealer cards then show all the 
		// cards if not then just show the first card and -- for
		// the rest of the cards
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
			if (dealerPoints == 0) dealerPoints = 1; // handle the ace
			
			System.out.println("Dealer Points: " + dealerPoints);
		}
		
		// Print player's cards and points
		System.out.print("Player: ");
		for (Card c : player) {
			System.out.print(c);
			System.out.print(" ");
		}
		
		System.out.println();
		System.out.println("Your Points: " + getPoints(player));
	}
	
	/**
	 * Gets the amount of points in a hand
	 *
	 * @param Vector<Card> hand - A hand
	 * @return int - The equivalent points in the hand
	 */
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
		
		// return if no ace
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