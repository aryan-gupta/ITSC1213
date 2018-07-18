
/**
 * Write a description of class Hangman here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hangman
{
    public enum HangmanStatus {
        GUESS_CORRECT, GUESS_REPEAT, GUESS_MISS, GUESS_WIN, GUESS_LOSE, GUESS_INVALID
    }
    
    private String mSentence;
    private StringBuilder mGuessedSentence; // not needed but will simplify algorithms
    private StringBuilder mGuesses;
    private int mNumMissed;
    
    // https://stackoverflow.com/questions/2816123/can-a-constructor-in-java-be-private
    // I know in c++, this was an old way to prevent users from calling certain
    // functions. This was before =delete feature was added. 
    // Link also answers a question had on how to make a function whose sole purpose
    // is to set up constants, and prevent code redundancy. 
    private Hangman() { /* Do Nothing */ }
    
    public Hangman(String sentence) {
        setSentence(sentence);
    }
    
    public HangmanStatus addGuess(char guess) {
        // https://www.javatpoint.com/java-char-to-string
        String guessStr = Character.toString(guess);
		
        // if we already guessed it then we want to 
        // return without doing anything
        if (mGuesses.indexOf(guessStr) != -1) {
            return HangmanStatus.GUESS_REPEAT;
        }
        
        mGuesses.append(guess);
        
        // if the guess is not in our sentence then we want 
        // to return we missed
        if (mSentence.indexOf(guessStr) == -1) {
            ++mNumMissed;
            // if we have made too many misses then we lose
            if (mNumMissed > 10) return HangmanStatus.GUESS_LOSE;
            return HangmanStatus.GUESS_MISS;
        }
        
        // if we get here then we have a guess that is valid
        // and is in our string. We want to replace all instances
        // of our guess and return
        for (int ii = 0; ii < mGuessedSentence.length(); ++ii) {
            ii = mSentence.indexOf(guess, ii);
            if (ii == -1) break;
            mGuessedSentence.replace(ii, ii + 1, guessStr);
        }
        
        // check if we have guessed all the letters
        if (mGuessedSentence.indexOf("_") == -1) {
            return HangmanStatus.GUESS_WIN;
        }
        
        return HangmanStatus.GUESS_CORRECT;
    }
    
    public HangmanStatus tryGuess(String str) {
        // maybe we should charge for full sentence guesses
        if (str.compareTo(mSentence) == 0)
            return HangmanStatus.GUESS_WIN;
        return HangmanStatus.GUESS_MISS;
    }
    
    public void setSentence(String sentence) {
        mSentence = new String(sentence);
        // https://stackoverflow.com/questions/1235179/simple-way-to-repeat-a-string-in-java
		//mGuessedSentence = new StringBuilder(StringUtils.repeat("_", sentence.length()));
		//mGuessedSentence = new StringBuilder(new String("_").repeat(sentence.length()));
		mGuessedSentence = new StringBuilder(new String(new char[sentence.length()]).replace("\0", "_"));
        mGuesses = new StringBuilder(""); // empty
        mNumMissed = 0;
		
		// we want the spaces to be freebees
		for (int ii = 0; ii < mGuessedSentence.length(); ++ii) {
            ii = mSentence.indexOf(' ', ii);
            if (ii == -1) break;
            mGuessedSentence.replace(ii, ii + 1, " ");
        }
    }
    
    public String getSentence() {
        return mSentence;
    }
    
    public String getGuessesChars() {
        return mGuesses.toString();
    }
    
    public String getGessedSentence() {
        return mGuessedSentence.toString();
    }
    
    private static String numGuess2str(int numGuess) {
        switch (numGuess) {
            case 0: return
                "   |    \n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "        \n";
                
            case 1: return
                "   |    \n" +
                "   O    \n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "        \n";
                
            case 2: return
                "   |    \n" +
                "   O    \n" +
                "   |    \n" +
                "   |    \n" +
                "        \n" +
                "        \n";
            
            case 3: return
                "   |    \n" +
                "   O    \n" +
                "  /|    \n" +
                " / |    \n" +
                "        \n" +
                "        \n";
            
            case 4: return
                "   |    \n" +
                "   O    \n" +
                "  /|\\   \n" +
                " / | \\  \n" +
                "        \n" +
                "        \n";
            
            case 5: return
                "   |    \n" +
                "   O    \n" +
                "  /|\\   \n" +
                "_/ | \\  \n" +
                "        \n" +
                "        \n";
            
            case 6: return
                "   |    \n" +
                "   O    \n" +
                "  /|\\   \n" +
                "_/ | \\_ \n" +
                "        \n" +
                "        \n";
            
            case 7: return
                "   |    \n" +
                "   O    \n" +
                "  /|\\   \n" +
                "_/ | \\_ \n" +
                "  /     \n" +
                " /      \n";
            
            case 8: return
                "   |    \n" +
                "   O    \n" +
                "  /|\\   \n" +
                "_/ | \\_ \n" +
                "  /     \n" +
                "_/      \n";
            
            case 9: return
                "   |    \n" +
                "   O    \n" +
                "  /|\\   \n" +
                "_/ | \\_ \n" +
                "  / \\   \n" +
                "_/   \\  \n";
            
            case 10: return
                "   |    \n" +
                "   O    \n" +
                "  /|\\   \n" +
                "_/ | \\_ \n" +
                "  / \\   \n" +
                "_/   \\_ \n";
                
            default: return "YOU FAILED!";
        }
        
    }
    
    public String toString() {
        return
        numGuess2str(mNumMissed) + "\n" +
        "Guessed Sentence: " + mGuessedSentence + "\n" +
        "Guessed Letters : " + mGuesses + "\n";
    }
}
