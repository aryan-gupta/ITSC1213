
/**
 * A player on the Craps board. This was orginally designed as a 2 player game,
 * however, after further clarification from the TA. minor edits was made to
 * make this single player
 *
 * @author Aryan Gupta
 * @version 1.0
 */

import java.util.Scanner;

public class Player
{
    // The states of the Player
    public enum TurnStates {
        WIN, LOSE, EXIT
    }
    
    private int mPoints; // temp value for the thrower's points
    private Craps mBoard; // the dice
    
    /**
     * Player Constructor. Creates a player
     *
     * @param cp The Dice "pointer"
     */
    public Player(Craps cp) {
        mPoints = 0;
        mBoard = cp;
    }
    
    /**
     * Method doTurn. Does a turn
     *
     * @return The state of the player (WIN/LOSE/EXIT)
     */
    public TurnStates doTurn() {
        Scanner in = new Scanner(System.in);
        char response;
        
        do {
            System.out.print("Type r to roll, e to exit:: ");
            response = in.next().charAt(0);
            
            if (response == 'e')
                return TurnStates.EXIT;
        } while (response != 'r');
        
        int sum = mBoard.getSum();
        
        System.out.println("You rolled a " + sum);
        
        if (sum == 7 || sum == 11) 
            return TurnStates.WIN; // return because we win
            
        if (sum == 2 || sum == 3 || sum == 12)
            return TurnStates.LOSE; // we lost so return
        
        // anything else falls through here
        mPoints = sum;
        System.out.println("You need a " + mPoints);
        
        do {
            do {
                System.out.print("Type r to roll, e to exit:: ");
                response = in.next().charAt(0);
                
                if (response == 'e')
                    return TurnStates.EXIT;
            } while (response != 'r');
        
            sum = mBoard.getSum();
            System.out.println("You rolled a " + sum + ". You need a " + mPoints);
            
            if (sum == 7)
                return TurnStates.LOSE; // we lose
            
            if (sum == mPoints)
                return TurnStates.WIN; // We win
        } while (true);
    }
}
