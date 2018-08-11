/**
 * Plays a game of Connect 4 or 4-in-a-row
 *
 * @author Manuel Diaz, Efrem Gebrekidane, Aryan Gupta
 * @version 1.0
 * 
 * To summarized work load:
 * We work together in lab the day the assigment was created. Almost the whole game was finished this day
 * that is way some code have three names
 * we finish the main class and the Board class working together the same day. 
 * then Aryan added two more methods to the board class and some comments
 * Efrem added code for the validation of inputs and a Ai feature to the computer's turn
 * Manuel commented all methods left in the board class, added comments for each of the parts in main and debugged code
 * to be ready to submit
 */
import java.util.*;
public class Connect4{
    public static void main(String[] args){
        //objects
        Board board = new Board();
        Scanner input = new Scanner(System.in);
        //variables
        int choice = 0;
        int column;
        char playAgain = 'y', computerChip = ' ', playerChip= ' ';
		int playerWins = 0, computerWins = 0;
        
        //This is the initial menu that ask and prompts the
        //user to select 1 to play or 2 to exit
        System.out.println("Welcome to Connect 4");
        System.out.println("1. Play");
        System.out.println("2. Quit");
        choice = input.nextInt();
        
        //This loop makes sure that the user picks a valid choice of 1 or 2
        //else it will dislay invalid choice and prompts the user to enter
        //a choice again
        while (choice < 1 || choice > 2)
        {
            System.out.println("****INVALID CHOICE***");
            System.out.println("Please Enter a 1 or a 2");
            System.out.println("1. Play");
            System.out.println("2. Quit");
            choice = input.nextInt();
        }
        //if user inputs 1 then the game starts
        if (choice == 1)
        {
            //This loop will keep looping util the user inputs 'n'
            //to exit the game
            while (playAgain == 'Y' || playAgain == 'y')
            {
                // This is the manu to select the user chip
                //the user can aither pick X or Y
                System.out.println("Select a chip to play");
                System.out.println("1) X");
                System.out.println("2) Y");
                System.out.print("Choose a piece:: ");
                choice = input.nextInt();
                char winner = 0;
                //The loop below is to make sure the user inputs
                //a valid selection for the chip, otherwise he/she will be prompt 
                //to choose a chip again
                while (choice < 1 || choice > 2)
                {
                    System.out.println("***INVALID CHOICE***");
                    System.out.println("Please enter a 1 or a 2");
                    System.out.println("1) X");
                    System.out.println("2) Y");
                    System.out.print("Choose a piece:: ");
                    choice = input.nextInt();
                } 
                //Depending on the user choice the playerChip and the computerChip
                // get a value asign
                if (choice == 1){
                    playerChip='X';
                    computerChip='Y';
                }
                else if (choice==2){
                    playerChip = 'Y';
                    computerChip = 'X';
                }

                ////////Game starts////////////
                while(!board.getFill() || board.getWinner() != '\0')
                {
                    System.out.println(board.toString());
                    //play the game
                    System.out.print("Which column do you want to place the chip 0-6:: ");
                    column = input.nextInt();
                    // checking or validating for the user column input
                    while (column < 0 || column > 6)
                    {
                        System.out.println("***INVALID NUMBER OF COLUMS ENTERED****");
                        System.out.print("Which column do you want to place the chip 0-6:: ");
                        column = input.nextInt();
                    }
                    
                    //check next place available in the column selected
                    board.place(column, playerChip);
                    //check if there are any winners
                    winner = board.getWinner();
                    if (winner != 0)
                    //if there is a winner exit the loop 
                        break;

                    //computer turn
                    // generate a random number 1 or 2
                    int rand = (int)(Math.random() * 2) + 1;
                    // if the random number is 1 computer will choose the same column as the user
                    if(rand == 1)
                    {
                        int piece = column;
                        board.place(piece, computerChip);
                        winner = board.getWinner();
                    }
                    // if the random number is 2 the computer will choose a random column
                    else
                    {
                        int piece = (int)(Math.random() * 7);
                        board.place(piece, computerChip);
                        winner = board.getWinner();
                    }
                } 
                // we want to print out the board one last time
                System.out.println(board.toString());
                
                //depending on who wins a massage is display stating the winner
                if (winner == playerChip)
                {
                    System.out.println("The player has won!!");
					++playerWins;
                } 
                else if (winner == computerChip)
                {
                    System.out.println("The computer has won!!");
					++computerWins;
                } else if (board.getFill()) {
					System.out.println("It's a Tie!");
				}
                
                //ask ghe user if the want to play again
                System.out.print("Do you want to play again Y/N:: ");
                playAgain = input.next( ).charAt(0); 
                //displays a massage according to user selection
                if(playAgain == 'n' || playAgain == 'N'){
					System.out.println("Player Wins: " + playerWins);
					System.out.println("Computer Wins: " + computerWins);
                    System.out.println("GoodBye");
                }
                //reinitializes the Board and the winner for the next game
                board = new Board();
                winner = 0;
            }
        }
        else 
        {
			System.out.println("Player Wins: " + playerWins);
			System.out.println("Computer Wins: " + computerWins);
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
        - \t --> ____ (bluej doesn't like tabs, tabs > spaces)
    - Grammar fixes that were not merged with Efrem's edit
    - Fix wrong use of board1 vs board variable
    - Fix bug where board isnt cleared when we choose to play again
    - Merged Manuel's edits into final version (Merge Strategy: Manuel's)
        - After a review of both codes from Manuel and Efrem, I decided Manuel's
          code was better as it reduced code redundancy. Original in git repo
        - Manuel's code addressed the last 2 fixes that I did (b1 vs b var) (b isnt cleared)
	- Add "Keeps track of how many rounds the player has won, lost, and tied."
	- Add computer/player tie situation
*/
