
/**
 * Write a description of class HangmanDriver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HangmanDriver extends Thread {
	// https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html
	// This is cool, though kinda annoying. I dont have c/c++ compiler installed on
	// my computer anymore (moved to WSL/Kali Linux) so I had to reinstall cywgin
	// I hate these gnu/gcc ports but oh well. 
	// After coding this, kodos for Java for making it so easy to do this. 
	// color me impressed
	
	// First 'compile' this class with javac -h . HangmanDriver.java
	// this will create a c/cpp header file with our function. 
	// write our c program that will do whatever we want
	// compile with directions in the c file
	// use our library!
	private static boolean useCLibrary;
	
	static {
		try {
			System.loadLibrary("clear_screen"); // loads clear_screen
			useCLibrary = true;
		} catch (java.lang.UnsatisfiedLinkError e) {
			useCLibrary = false;
		}
	}
	
	private native static void clear_screen_win_api();
   
    public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		
		String guessStr = null;
		
		clearScreen();
		System.out.println();
		
		do {
			System.out.println("Welcome to Aryan's ASCII Hangman Game!");
			System.out.println("Please pick from one of these options");
			System.out.println("0. How to Play (There are some new rules)");
			System.out.println("1. Hangman with a Funny Quote");
			System.out.println("2. Hangman with a Wisdom Quote");
			System.out.println("3. Hangman with a Love Quote");
			System.out.println("4. Hangman with a Motivational Quote");
			System.out.println("5. Hangman with a Custom Quote");
			
			System.out.print("Your Choice:: ");
			String choiceStr = in.nextLine();
			if (choiceStr.equals("exit")) return;
			
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
					in.nextLine();
					clearScreen();
					System.out.println();
					break;
				}
				case 1: guessStr = getRandomStringFromFile("funny.txt"); break;
				case 2: guessStr = getRandomStringFromFile("wisdom.txt"); break;
				case 3: guessStr = getRandomStringFromFile("love.txt"); break;
				case 4: guessStr = getRandomStringFromFile("motive.txt"); break;
				case 5: guessStr = getHiddenString(); break;
				default: {
					clearScreen();
					System.out.println("Wrong choice, type a number between 0 and 5");
					break;
				}
			}
		} while (guessStr == null);
		
		//System.out.println(guessStr);
		
		Hangman dude = new Hangman(guessStr);
		//System.out.print('\u000C');
		//for (int i = 0; i < 50; ++i) System.out.println();
		clearScreen();
		System.out.println("");
		
		while (true) {
			System.out.println(dude);
			
			System.out.print("Enter your guess:: ");
			String buffer = in.nextLine();
			Hangman.HangmanStatus status = Hangman.HangmanStatus.GUESS_INVALID;
			
			if (buffer.toLowerCase().equals("exit")) break;
			status = dude.addGuess(buffer);
			
			//System.out.print('\u000C');
			//for (int i = 0; i < 50; ++i) System.out.println();
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
					System.out.println(dude);
				return;
				
				case GUESS_LOSE:
					System.out.println("YOU LOSE!");
					System.out.println(dude);
				return;
				
				default:
			}
		}
	}

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
		// https://stackoverflow.com/questions/6032118/make-the-console-wait-for-a-user-input-to-close
		// didnt want to create a scanner instance just for this, needed static method
		try {
			System.in.read();
		} catch (java.io.IOException e) {
			System.out.println("Something went wrong that the programmer was too dumb to foresee. Exiting...");
			System.exit(0);
		}
	}
	
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
	
	private static String getHiddenString() {
		// https://stackoverflow.com/questions/8138411/masking-password-input-from-the-console-java
		// http://www.javaxt.com/Tutorials/Console_Apps/How_To_Prompt_a_User_for_a_Username_and_Password_from_the_Command_Line
		// Alright let me see if I can decyper this. 
		// First, we try to get the console, this is the preferred method for the cmd and powershell
		// however if we can not get the instance and we are using some weird crappy terminal (like bluej)
		// then we will clear the screen (to prepare for our secure string)
		// while the user is trying to enter a secure string we create another thread. This thread
		// will continue to clear the screen and rewrite the entry prompt so the user will perceive 
		// that they aren't 'typing' anything. One thing I actually like about java is that it makes
		// multi-threading so much easier, especially with atomics. The original code for this from 
		// the link above actually used \b (backspace char) to erase what the user typed, however I realized
		// bluej doesn't support this character. So I decided to use the clearScreen/reprint prompt method
		// This could be made into a separate class but pros > cons
		// After writing this method, I realized that BlueJ uses a separate input box that it sends to input
		// stream after we press the enter key. 
		java.io.Console con = System.console();
		if (con != null) {
			char sentece[] = con.readPassword("Enter your sentence (it will not show)\n:: ");
			return new String(sentece);
		} else {
			clearScreen();
			String password = "";
			HangmanDriver consoleEraser = new HangmanDriver();
			System.out.print("Enter your sentence (it will not show)\n:: ");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			consoleEraser.start();
			try {
				password = in.readLine();
			}
			catch (java.io.IOException e){
				System.out.println("Error trying to read your password!");
				System.exit(1);
			}

			consoleEraser.halt();
			clearScreen();

			return password;
		}
	}
	
	// Ahhh the joys of multi-threading. 
	private boolean running = true;
	
	public void run() {
		while (running) {
			clearScreen();
			System.out.print("Enter your sentence (it will not show)\n:: ");
			try {
				Thread.currentThread().sleep(1);
			} catch(InterruptedException e) {
				break;
			}
		}
	}
	
	public synchronized void halt() {
		running = false;
	}
	
	private static void clearScreen() {
		if (useCLibrary) {
			clear_screen_win_api();
		} else {
			for (int i = 0; i < 100; ++i) System.out.print('\n');
			System.out.print('\u000C');
			// System.out.print('\n');
		}
	}
}