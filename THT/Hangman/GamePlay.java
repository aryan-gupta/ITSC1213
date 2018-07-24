import java.util.*;
/**
 * Write a description of class GamePlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GamePlay
{
   public static void main(String[ ] args)
   {
       Scanner in = new Scanner(System.in);
       char guess;
       GameWord myPhrase = new GameWord("one day at a time" );
       boolean [ ] used = new boolean[255];
       
       System.out.println("\n" + myPhrase.toString( ));
       while(!myPhrase.checkWin( ) && ! myPhrase.getGameOver( ))
       {
         do{
           System.out.print("\nEnter your character: ");
           guess = in.nextLine( ).charAt(0);
           if(used[guess]) System.out.println("That letter already guessed.");
        }while (used[guess]);
         used[guess] = true; 
         myPhrase.find(guess);
         System.out.println("\n" + myPhrase.toString( ));
       }
    
       if(myPhrase.checkWin( ))
       {
         System.out.println("You got it!");
       }
        
       System.out.println("Game Over");  
       System.out.println("Game Over"); 
       System.out.println("Game Over"); 
       System.out.println("Game Over"); 
    }
}
