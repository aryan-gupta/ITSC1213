
/**
 * Runs a Craps game
 *
 * @author Aryan Gupta
 * @version 1.0
 */

import java.util.Scanner;

public class CrapsRunner
{
    public static void main() {
        Craps board = new Craps();
        Player p1 = new Player(board);
        
        do {
            Player.TurnStates state = p1.doTurn();
            if (state == Player.TurnStates.WIN) {
                System.out.println("You Win!");
            } else if (state == Player.TurnStates.LOSE) {
                System.out.println("You Lose");   
            } else {
                System.out.println("Good Bye!");
                return;
            }
            
            Scanner in = new Scanner(System.in);
            char response;
            do {
                System.out.print("Do you want to play again? :: ");
                response = in.next().charAt(0);
                
                if (response == 'y')
                    break;
                else
                    return;
            } while (true);
            
            
        } while(true);
    }
}
