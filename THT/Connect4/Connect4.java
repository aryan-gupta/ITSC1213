
/**
 * Plays a game of Connect 4 or 4-in-a-row
 *
 * @author Manuel Diaz, Efrem Gebrekidane, Aryan Gupta
 * @version 1.0
 */
import java.util.*;
public class Connect4{
	private static Scanner input;
    public static void main(String[] args){
        //objects
        Board board = new Board();
        input = new Scanner(System.in);
		int choice = 0;
		
        while (choice != 2){
            System.out.println("Welcome to connect 4" +
                               "\n1. Play" +
                               "\n2. Quit");
			do {
				try {
					choice = input.nextInt();
				} catch (java.util.InputMismatchException e) {
					input.next();
					System.out.println("Please enter a column number");
					choice = 0;
				}
			} while (choice < 1); /// @todo check if 2 or greater
			
            if (choice == 1){
				// ask for char
				char playerChar = getPlayerChar();
				char computChar;
				// set the computer char to the other one
				if (playerChar == 'X')
					computChar = 'Y';
				else
					computChar = 'X';
                //and check win to the while loop
				char winner = 0;
                do {
                    System.out.println(board.toString());
                    //play the game
                    System.out.println("Which column do you want to place the chip 0-6");
                    //add if statement for outbounce check
					int column = -1;
					do {
						try {
							column = input.nextInt();
						} catch (java.util.InputMismatchException e) {
							input.next();
							System.out.println("Please enter a column number");
						}
					} while (column < 0); /// @todo check bounds on column number
                    //check next place available
                    board.place(column, playerChar);
                    winner = board.getWinner();
					if (winner != 0)
						break;

                    //computer
                    int rand = (int)(Math.random() * 7);
                    board.place(rand, computChar);
                    winner = board.getWinner();
                } while (winner == 0);
				
				// we want to print out the board one last time
				System.out.println(board.toString());
				
				if (winner == playerChar) {
					System.out.println("The player has won!!");
				} else {
					System.out.println("The computer has won!!");
				}
            }
            else if (choice ==2){
                System.out.println("GoodBye");
            }
        }
    }
	
	/** 
	 * Gets a char from the user to use as a player piece on the board
	 *
	 * @author Aryan Gupta
	 * @return char - The char that the user choose
	 */
	private static char getPlayerChar() {
		char piece = 0;
		do {
			System.out.println("Choose a piece");
			System.out.println("1) X");
			System.out.println("2) Y");
			System.out.print(":: ");
			
			try {
				int choice = input.nextInt();
				switch (choice) {
					case 1: piece = 'X'; break;
					case 2: piece = 'Y'; break;
					default: throw new java.lang.IllegalArgumentException();
				}
			} catch (java.util.InputMismatchException e) {
				input.next();
				System.out.println("Please enter a number...");
			} catch (java.lang.IllegalArgumentException e) {
				System.out.println("Please enter a 1 or a 2...");
			}
			
		} while (piece == 0);
		return piece;
	}
}

/* CHANGELOG
 Aug 1 - Aryan
    - Add doc for class
    - Formating
    - Spell check
 Aug 4 - Aryan
    - Add ability for player to choose their piece
	- Correct some grammar
	- Display who the winner is
	- Add input exception handling
*/
