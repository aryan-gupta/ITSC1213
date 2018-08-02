
/**
 * Plays a game of Connect 4 or 4-in-a-row
 *
 * @author Manuel Diaz, Efrem Gebrekidane, Aryan Gupta
 * @version 1.0
 */
import java.util.*;
public class Connect4{
    public static void main(String[] args){
        //objects
        Board board = new Board();
        Scanner input = new Scanner(System.in);
        int choice = 0, columm = -1;

        while (choice != 2){
            System.out.println("Welcome to connect 4" +
                               "\n1. PLay" +
                               "\n2. quit");
            choice = input.nextInt();
            if (choice == 1){
                //and check win to the while loop
                while(!board.getFill() || board.getWinner() != '\0'){
                    System.out.println(board.toString());
                    //play the game
                    System.out.println("Which column do you want to place the chip 0-6");
                    //add if statement for outbounce check
                    columm = input.nextInt();


                    //check next place available
                    board.place(columm, 'x');
                    if (board.getWinner() != '\0') {
                        System.out.println("Someone won");
                    }

                    //computer
                    int rand = (int)(Math.random() * 7);
                    board.place(rand, 'y');
                    //check win here/////////
                } 
            }
            else if (choice ==2){
                System.out.println("GoodBye");
            }
        }
    }
}

/* CHANGELOG
 Aug 1 - Aryan
    - Add doc for class
    - Formating
    - Spell check
*/
