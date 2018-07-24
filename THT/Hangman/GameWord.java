
/**
 * Write a description of class GameWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWord
{
   private String phrase;
   private StringBuilder inProgress;
   private int numberWrongGuesses;
   private StringBuilder state;
   private boolean gameOver;
   
   public GameWord( )
   {
       inProgress = new StringBuilder( );
       phrase = new String( );
       numberWrongGuesses = 0;
       state = new StringBuilder( );
       gameOver = false;
    }
    
    public GameWord(String phraseToGuess)
    {
        phrase = new String(phraseToGuess);
        state = new StringBuilder( );
        inProgress = new StringBuilder( );
        for(int k = 0; k < phrase.length( ); k++)
        {
            inProgress.append( "_ ");
        }
        numberWrongGuesses = 0;
        gameOver = false;
    }
        
    public void setPhrase(String phraseToGuess)
    {
        phrase = new String(phraseToGuess);
        state = new StringBuilder( );
        inProgress = new StringBuilder( );
        for(int k = 0; k < phrase.length( ); k++)
        {
            inProgress.append( "_ ");
        }
        numberWrongGuesses = 0;
        state = new StringBuilder( );
        gameOver = false;
    }
    
    public boolean getGameOver( )
    {
        return gameOver;
    }
    public void find(char symbol)
    {
        int count = 0;
        int pos = 0;
        pos = phrase.indexOf(symbol);
        while(pos != -1)
        { 
          count++;
          inProgress.setCharAt(pos*2,symbol);
          pos = phrase.indexOf(symbol, pos + 1);;
        }
        if(count == 0)
        {
            numberWrongGuesses++;
            updateState( );
        }
        
        
        
    }
    
  public boolean checkWin( )
   {
      boolean match = true;
       for(int k = 0, m = 0; k < phrase.length( ) && match; k++, m += 2)
      {
          if(phrase.charAt(k) != inProgress.charAt(m))
          {
              match = false;
         
          }
      }
      return match;
    }
   
    
   private void updateState( )
   { 
      
          
       switch(numberWrongGuesses)
       {
           case 1: state.append("\n\t\t\t\t\t 0");
                   break;
           case 2: state.append( "\n\t\t\t\t       /");
                   break;
           case 3: state.append(" |");
                   break;
           case 4: state.append( " \\");
                   break;
           case 5: state.append("\n\t\t\t\t         |");
                   break;
           case 6: state.append("\n\t\t\t\t        /");
                   break;
           case 7: state.append(" \\\n\n\t\t\t\t      OH NO!!\n");
                   gameOver = true;
                   
        
      }
      
  }
  
  public String toString(  )
  {
      return new String("\n***************************************\n" 
            + inProgress + "\n" + state.toString( ));
  }
}