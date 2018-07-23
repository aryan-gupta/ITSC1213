
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
    private StringBuilder mGuessedLetters;
	private java.util.Vector<String> mGuessedWords;
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
    
    public HangmanStatus addGuess(String guess) {
		if (guess.length() > 1) return tryString(guess.toLowerCase());
		else                    return tryChar(Character.toLowerCase(guess.charAt(0)));
	}
    
    public void setSentence(String sentence) {
		sentence = " " + sentence + " ";
        mSentence = new String(sentence);
        // https://stackoverflow.com/questions/1235179/simple-way-to-repeat-a-string-in-java
		//mGuessedSentence = new StringBuilder(StringUtils.repeat("_", sentence.length()));
		//mGuessedSentence = new StringBuilder(new String("_").repeat(sentence.length()));
		mGuessedSentence = new StringBuilder(new String(new char[sentence.length()]).replace("\0", "_"));
        mGuessedLetters = new StringBuilder(""); // empty
		mGuessedWords = new java.util.Vector<String>();
        mNumMissed = 0;
		
		// we want anything that isnt a letter to be freebees
		for (int ii = 0; ii < mGuessedSentence.length(); ++ii) {
			char ch = mSentence.charAt(ii);
            if (!Character.isLetter(ch)) mGuessedSentence.replace(ii, ii + 1, Character.toString(ch));
        }
    }
    
    public String getSentence() {
        return mSentence;
    }
    
    public String getGuessesChars() {
        return mGuessedLetters.toString();
    }
    
    public String getGessedSentence() {
        return mGuessedSentence.toString();
    }
    
	public String getGuessedWordsString() {
		if (mGuessedWords.isEmpty())
			return "";
		
		String ret = "";
		for (String str : mGuessedWords) {
			ret += str;
			ret += ", ";
		}
		// remove the trailing comma
		return ret.substring(0, ret.length() - 2); // hacky but works
	}
	
	public String[] getGuessedWords() {
		// https://stackoverflow.com/questions/7500259/how-to-convert-vector-to-string-array-in-java
		// https://stackoverflow.com/questions/28392705/difference-between-toarrayt-a-and-toarray
		// https://stackoverflow.com/questions/1018750/how-to-convert-object-array-to-string-array-in-java/13647072#13647072
		return mGuessedWords.toArray(new String[mGuessedWords.size()]);
	}
	
    private static String numGuess2str(int numGuess) {
        switch (numGuess) {
            case 0: return
                " ______    \n" +
                " |    |    \n" +
                " |         \n" +
                " |         \n" +
                " |         \n" +
                " |         \n" +
                " |         \n" +
                "/ \\       \n";
                
            case 1: return
				" ______    \n" +
                " |    |    \n" +
                " |    O    \n" +
                " |         \n" +
                " |         \n" +
                " |         \n" +
                " |         \n" +
                "/ \\       \n";
                
            case 2: return
				" ______    \n" +
                " |    |    \n" +
                " |    O    \n" +
                " |    |    \n" +
                " |    |    \n" +
                " |         \n" +
                " |         \n" +
                "/ \\       \n";
            
            case 3: return
				" ______    \n" +
                " |    |    \n" +
                " |    O    \n" +
                " |   /|    \n" +
                " |  / |    \n" +
                " |         \n" +
                " |         \n" +
                "/ \\       \n";
            
            case 4: return
				" ______     \n" +
                " |    |     \n" +
                " |    O     \n" +
                " |   /|\\   \n" +
                " |  / | \\  \n" +
                " |          \n" +
                " |          \n" +
                "/ \\        \n";
            
            case 5: return
				" ______     \n" +
                " |    |     \n" +
                " |    O     \n" +
                " |   /|\\   \n" +
                " | _/ | \\  \n" +
                " |          \n" +
                " |          \n" +
                "/ \\        \n";
            
            case 6: return
				" ______     \n" +
                " |    |     \n" +
                " |    O     \n" +
                " |   /|\\   \n" +
                " | _/ | \\_ \n" +
                " |          \n" +
                " |          \n" +
                "/ \\        \n";
            
            case 7: return
				" ______     \n" +
                " |    |     \n" +
                " |    O     \n" +
                " |   /|\\   \n" +
                " | _/ | \\_ \n" +
                " |   /      \n" +
                " |  /       \n" +
				"/ \\        \n";
            
            case 8: return
				" ______     \n" +
                " |    |     \n" +
                " |    O     \n" +
                " |   /|\\   \n" +
                " | _/ | \\_ \n" +
                " |   /      \n" +
                " | _/       \n" +
				"/ \\        \n";
            
            case 9: return
				" ______     \n" +
                " |    |     \n" +
                " |    O     \n" +
                " |   /|\\   \n" +
                " | _/ | \\_ \n" +
                " |   / \\   \n" +
                " | _/   \\  \n" + 
				"/ \\        \n" ;
            
            case 10: return
				" ______     \n" +
                " |    |     \n" +
                " |    O     \n" +
                " |   /|\\   \n" +
                " | _/ | \\_ \n" +
                " |   / \\   \n" +
                " | _/   \\_ \n" +
				"/ \\        \n" ;
                
            default: return
				" ______     \n" +
                " |    |     \n" +
                " |    O     \n" +
                " |   /|\\   \n" +
                " | _/ | \\_ \n" +
                " |   / \\   \n" +
                " | _/   \\_ \n" +
				"/ \\        \n" ;
        }
        
    }
    
	private HangmanStatus tryChar(char guess) {
		// https://www.javatpoint.com/java-char-to-string
        String guessStr = Character.toString(guess);
		
        // if we already guessed it then we want to 
        // return without doing anything
        if (mGuessedLetters.indexOf(guessStr) != -1) {
            return HangmanStatus.GUESS_REPEAT;
        }
        
        mGuessedLetters.append(guess);
        
        // if the guess is not in our sentence then we want 
        // to return we missed
        if (mSentence.toLowerCase().indexOf(guessStr) == -1) {
            ++mNumMissed;
            // if we have made too many misses then we lose
            if (mNumMissed > 10) return HangmanStatus.GUESS_LOSE;
            return HangmanStatus.GUESS_MISS;
        }
        
        // if we get here then we have a guess that is valid
        // and is in our string. We want to replace all instances
        // of our guess and return. We replace with the original string
		// in order to preserve case of the string
        for (int ii = 0; ii < mGuessedSentence.length(); ++ii) {
            ii = mSentence.toLowerCase().indexOf(guess, ii);
            if (ii == -1) break;
            mGuessedSentence.replace(ii, ii + 1, mSentence.substring(ii, ii + 1));
        }
        
        // check if we have guessed all the letters
        if (mGuessedSentence.indexOf("_") == -1) {
            return HangmanStatus.GUESS_WIN;
        }
        
        return HangmanStatus.GUESS_CORRECT;	
	}
	
	private HangmanStatus tryString(String str) {
		// There is different states for our str,
		// 1) str can be an entire sentence guess
		// 2) str can be a multiword guess 
		// 3) str can just be a word guess
		
		if (mGuessedWords.contains(str)) {
			return HangmanStatus.GUESS_REPEAT;
		}
		
		mGuessedWords.addElement(str);
		
		// if our guess is equal to the string then we win
		if (str.equals(mSentence.toLowerCase()))
            return HangmanStatus.GUESS_WIN;
		
		// check if our str is in our original string (with spaces and everything)
		boolean found = false;
		int ii = -1;
		while (ii < mSentence.length()) {
			ii = mSentence.toLowerCase().indexOf(str, ++ii);
			if (ii == -1)
				break;
			// we want to check if this is surrounded by non-alpha character for it to considered 
			// a word. If it is surrounded by alpha char then it is part of a bigger word 
			// and we want to skip it
			if (Character.isLetter(mSentence.charAt(ii - 1)) || Character.isLetter(mSentence.charAt(ii + str.length())))
				continue;
			mGuessedSentence.replace(ii, ii + str.length(), mSentence.substring(ii, ii + str.length()));
			found = true;
		}
		
		if (mGuessedSentence.indexOf("_") == -1) {
            return HangmanStatus.GUESS_WIN;
        }
		
		// if we found our str guess in our original string
		if (found) {
			return HangmanStatus.GUESS_CORRECT;
		} else {
			++mNumMissed;
            if (mNumMissed > 10) return HangmanStatus.GUESS_LOSE;
			else                 return HangmanStatus.GUESS_MISS;
		}
    }
    
    public String toString() {
        return
        numGuess2str(mNumMissed) + "\n" +
        "Guessed Sentence: " + mGuessedSentence + "\n" +
        "Guessed Letters : " + mGuessedLetters + "\n" +
		"Guessed Words: " + getGuessedWordsString() + "\n";
    }
}
