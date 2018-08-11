/**
 * Plays a game of Connect 4 or 4-in-a-row
 *
 * To summarized work load:
 * We work together in lab the day the assigment was created. Almost the whole game was finished this day
 * that is way some code have three names
 * we finish the main class and the Board class working together the same day. 
 * then Aryan added two more methods to the board class and some comments
 * Efrem added code for the validation of inputs and a Ai feature to the computer's turn
 * Manuel commented all methods left in the board class, added comments for each of the parts in main and debugged code
 * to be ready to submit
 *
 * @author Manuel Diaz, Efrem Gebrekidane, Aryan Gupta
 * @version 1.0
 */
import java.util.*;
public class Connect4{
    public static void main(String[] args){
        //objects
        Board board;
        Board board1;
        Scanner input = new Scanner(System.in);
        int choice = 0;
        int column;
        
        char playAgain = 'y';
        System.out.println("Welcome to Connect 4");
        System.out.println("1. Play");
        System.out.println("2. Quit");
		choice = input.nextInt();
		while (choice < 1 || choice > 2)
		{
			System.out.println("****INVALID CHOICE***");
			System.out.println("Please Enter a 1 or a 2");
			System.out.println("Welcome to Connect 4");
			System.out.println("1. Play");
			System.out.println("2. Quit");
			choice = input.nextInt();
		}
        if (choice == 1)
        {
            while (playAgain == 'Y' || playAgain == 'y')
			{
				System.out.println("Choose a piece");
				System.out.println("1) X");
				System.out.println("2) Y");
				System.out.print(":: ");
				choice = input.nextInt();
				char winner = 0;
				
				while (choice < 1 || choice > 2)
				{
					System.out.println("***INVALID CHOICE***");
					System.out.println("Please enter a 1 or a 2");
					System.out.println("1) X");
					System.out.println("2) Y");
					System.out.print("Choose a piece: ");
					choice = input.nextInt();
				} 
			
				if (choice == 1)
				{
					board = new Board();
					while(!board.getFill() || board.getWinner() != '\0')
					{
						System.out.println(board.toString());
						//play the game
						System.out.println("Which column do you want to place the chip 0-6: ");
						
						column = input.nextInt();
						// checking or validating for the user column input
						while (column < 0 || column > 6)
						{
							System.out.println("***INVALID NUMBER OF COLUMS ENTERED****");
							System.out.println("Which column do you want to place the chip 0-6: ");
							column = input.nextInt();
						}
						
						//check next place available
						board.place(column, 'X');
						winner = board.getWinner();
						if (winner != 0)
							break;
	
						//computer turn
						// generate a random number 1 or 2
						int rand = (int)(Math.random() * 3) + 1;
						// if the random number is 1 computer will choose the same column as the user
						if(rand == 1)
						{
							int piece = column;
							board.place(piece, 'Y');
							winner = board.getWinner();
						}
						// if the random number is 2 the computer will choose a random column
						else
						{
							int piece = (int)(Math.random() * 7);
							board.place(piece, 'Y');
							winner = board.getWinner();
						}
					}
					
					// we want to print out the board one last time
					// System.out.println(board.toString());
					if (winner == 'X')
					{
						System.out.println("The player has won!!");
					} 
					else 
					{
						System.out.println("The computer has won!!");
					}
				}			
				else 
				{
					char winner1 = 0;
					while(!board1.getFill() || board1.getWinner() != '\0')
					{
						System.out.println(board1.toString());
						//play the game
						System.out.println("Which column do you want to place the chip 0-6: ");
						
						column = input.nextInt();
						// checking or validating for the user column input
						while (column < 0 || column > 6)
						{
							System.out.println("***INVALID NUMBER OF COLUMS ENTERED****");
							System.out.println("Which column do you want to place the chip 0-6: ");
							column = input.nextInt();
						}
						
						//check next place available
						board1.place(column, 'Y');
						winner1 = board1.getWinner();
						if (winner != 0)
							break;
	
						//computer turn 
						// generate a random number 1 or 2
						int rand = (int)(Math.random() * 3) + 1;
						// if the random number is 1 computer will choose the same column as the user
						if(rand == 1)
						{
							int piece = column;
							board1.place(piece, 'X');
							winner1 = board.getWinner();
						}
						// if the random number is 2 the computer will choose a random column
						else
						{
							int piece = (int)(Math.random() * 7);
							board1.place(piece, 'X');
							winner1 = board.getWinner();
						}
						
					}
					// we want to print out the board one last time
					// System.out.println(board1.toString());
					
					if (winner1 == 'Y')
					{
						System.out.println("The player has won!!");
					} 
					else 
					{
						System.out.println("The computer has won!!");
					}
				}
				
				System.out.println("Do you want to play again Y/N:");
				playAgain = input.next( ).charAt(0); 
		  }
		  
           
        }
        else 
        {
          System.out.println("GoodBye");
        }
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
 Aug 8 - Efrem
    - Add validation for all user input
    - Created a while loop which enables the user to play as much as they want
    - Created if/else statments which enabls the computer to have two choose on 
      chooseing it's colum 1. The same column as the user
                           2. a random column
Aug 10 - Manuel Diaz
    -commented most part of the main code
    -debug problems with the chip selection 
    -debug problems with printer computer choice
    -debug problems with computer AI
Aug 10 - Aryan
	- Formatting overhaul (blame bluej)
	- Grammar fixes that were not merged with Efrem's edit
	- Fix wrong use of board1 vs board variable
	- Fix bug where board isnt cleared when we choose to play again
	- Merged Manuel's edits into final version (Merge Strategy: Manuel's)
		- After a review of both codes from Manuel and Efrem, I decided Manuel's
		  code was better as it reduced code redundancy. Original in git repo
*/