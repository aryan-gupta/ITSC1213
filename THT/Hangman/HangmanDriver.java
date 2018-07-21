
/**
 * Write a description of class HangmanDriver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner;

public class HangmanDriver
{
    public static void main() {
		Scanner in = new Scanner(System.in);
		String guessStr = "abcxyzababz abc abc aaa bbb ccc abc";
		Hangman dude = new Hangman(guessStr);
		System.out.print('\u000C');
		System.out.println("");
		
		while (true) {
			System.out.println(dude);
			
			System.out.print("Enter your guess:: ");
			String buffer = in.next();
			Hangman.HangmanStatus status = Hangman.HangmanStatus.GUESS_INVALID;
			// check if we entered a char or an sentece guess
			if (buffer.length() > 1) {
				status = dude.tryGuess(buffer);
			} else {
				status = dude.addGuess(buffer.charAt(0));
			}
			
			System.out.print('\u000C');
			
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
}
