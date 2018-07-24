
/**
 * The Driver for our Hangman Game, runs a basic version of Hangman
 * I had some ideas for this game but uses some language features
 * that I'm not familiar with and needed some Google-fuu. After
 * talking with the professor and I was not allowed to use Google-fuu.
 * This is the game without the features I was unsure how to implement
 *
 * @author Aryan Gupta
 * @version 2.0
 */

import java.util.Scanner;

public class HangmanDriver {
	
	// This is probably bad practice but I need my Google-fuu powers 
	// to verify that
	private static Scanner in = new Scanner(System.in); // stores an instance of
	// the Scanner so all members of this class can access it
	
    /**
	 * Our program entry point
	 *
	 * @param String[] args - The commandline arguments
     */
    public static void main(String args[]) {
		while (true) {
			if (playGame()) 
				break;
			System.out.println("Would you like to play again?");
			System.out.print(":: ");
			char response = in.nextLine().toLowerCase().charAt(0);
			if (response != 'y')
				break;
		}
		System.out.println("Goodbye!");
	}
	
	/**
	 * Plays one game of Hangman
	 */
	public static boolean playGame() {
		String guessStr = null; // our while loop below will loop until
		// we get a valid string in this
		
		clearScreen();
		System.out.println(); // add a line for the error message (looks better if we do)
		
		do {
			System.out.println("Welcome to Aryan's ASCII Hangman Game!");
			System.out.println("Please pick from one of these options");
			System.out.println("0. How to Play (There are some new rules)");
			System.out.println("1. Hangman with a Funny Quote");
			System.out.println("2. Hangman with a Wisdom Quote");
			System.out.println("3. Hangman with a Love Quote");
			System.out.println("4. Hangman with a Motivational Quote");
			System.out.println("5. Hangman with a Custom Quote");
			System.out.println("exit. To exit the game");
			
			System.out.print("Your Choice:: ");
			String choiceStr = in.nextLine();
			if (choiceStr.equals("exit")) return true;;
			
			int choice = 0;
			try {
				choice = Integer.parseInt(choiceStr);
			} catch (java.lang.NumberFormatException e)  {
				clearScreen();
				System.out.println("Wrong choice, type a number between 0 and 5");
				continue;
			}
			
			switch (choice) {
				case 0: {
					showHelp();
					clearScreen();
					System.out.println();
					break;
				}
				case 1: guessStr = getRandomStringFromFile("funny.txt"); break;
				case 2: guessStr = getRandomStringFromFile("wisdom.txt"); break;
				case 3: guessStr = getRandomStringFromFile("love.txt"); break;
				case 4: guessStr = getRandomStringFromFile("motive.txt"); break;
				case 5: {
					System.out.print("Enter a string:: ");
					guessStr = in.nextLine();
					break;
				}
				default: {
					clearScreen();
					System.out.println("Wrong choice, type a number between 0 and 5");
					break;
				}
			}
		} while (guessStr == null);
		
		//System.out.println(guessStr);
		
		Hangman dude = new Hangman(guessStr);
		clearScreen();
		System.out.println(); // add an empty line for space for an error message
		
		while (true) {
			System.out.println(dude);
			
			System.out.print("Enter your guess:: ");
			String buffer = in.nextLine();
			Hangman.HangmanStatus status = Hangman.HangmanStatus.GUESS_INVALID;
			
			if (buffer.isEmpty()) {
				clearScreen();
				System.out.println("Please enter something...");
				continue;
			}
			
			if (buffer.toLowerCase().equals("exit")) return true;
			status = dude.addGuess(buffer);
			
			clearScreen();
			
			switch (status) {
				case GUESS_CORRECT:
					System.out.println("Correct!");
				break;
				
				case GUESS_REPEAT:
					System.out.println("You already guessed this!");
				break;
				
				case GUESS_MISS:
					System.out.println("Oops, sorry...");
				break;
				
				case GUESS_WIN:
					System.out.println("You Win!!!!!");
					System.out.println(dude); // print the dude one more time so the user can see the final state
				return false;
				
				case GUESS_LOSE:
					System.out.println("YOU LOSE!");
					System.out.println(dude);
				return false;
				
				default:
			}
		}
	}
	
	/**
	 * Shows the help text for the Hangman Game
	 */
	private static void showHelp() {
		clearScreen();
		System.out.println("The rules of Aryan's Hangman is simple.");
		System.out.println("You have a string that you must guess.");
		System.out.println("You have 10 chances, each chance a new body");
		System.out.println("part will be sewn to the noose.");
		System.out.println("You can type a letter, word, multiple words, or");
		System.out.println("an entire sentence, if the guess occurs multiple");
		System.out.println("times, then all occurrences will be added. ");
		System.out.print("Press <enter> to exit:: ");
		in.nextLine();
	}
	
	/**
	 * Returns a random line from a file specified from the file 
	 *
	 * @param String filename - The name of the file we should read from
	 * @return String - A random line from the file
	 */
	private static String getRandomStringFromFile(String filename) {
		Scanner in = null;
		try {
			in = new Scanner(new java.io.File(filename));
		} catch (java.io.FileNotFoundException e) {
			System.out.println("FILE NOT FOUND");
		}
		java.util.Random rand = new java.util.Random(System.currentTimeMillis());
		
		int lineNum = rand.nextInt(10);
		
		while (lineNum --> 0) { String dummy = in.nextLine(); }
		
		return in.nextLine();
	}
	
	/** 
	 * Clears the terminal using the BlueJ voodoo magic terminal command
	 */
	private static void clearScreen() {
		System.out.print('\u000C');
	}
}