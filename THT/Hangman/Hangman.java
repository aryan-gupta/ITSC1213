
/**
 * This class represents a Hangman game. The class stores
 * the state of the hanged man and the state of the sentence
 * we have to guess. It allows user to input a new guess 
 * whether that be a word guess, multi-word guess, or the
 * classic single letter guess. This class keeps track of
 * the letter and word guesses so you dont double guess
 * The class makes sure that there is no difference between
 * guessing a lowercase or uppercase letter or word
 * (case-insensitive), however it makes sure that the sentence
 * printed on the screen has the original case. This class also
 * gives any non-letter characters as a freebee. This code is 
 * compatible with the legacy example code given by the professor
 *
 * @author Aryan Gupta
 * @version 3.0
 */
public class Hangman
{	
	/** 
	 * This enum is used to return the current state of the Hangman. For example,
	 * if our guess was not in the original string, it will return GUESS_MISS.
	 * This enum can be replaced by a char as the state handler, but I prefer
	 * enums
	 */
    public enum HangmanStatus {
        GUESS_CORRECT, GUESS_REPEAT, GUESS_MISS, GUESS_WIN, GUESS_LOSE, GUESS_INVALID
    }
    
    private String mSentence; // The original string 
    private StringBuilder mGuessedSentence; // The parts of the sentence we have guessed so far
    private StringBuilder mGuessedLetters; // letters we have guessed
	private java.util.Vector<String> mGuessedWords; // words we have guessed
    private int mNumMissed; // Number of missed trys
	private HangmanStatus mLastStatus; // Stores last status. Exists because of assignment requirements
    
	/**
	 * The default constructor, this sets up the class with a blank string
	 */
    public Hangman() {
		setPhrase("");
	}
    
	/**
	 * C'tor that creates this class with an string for the hangman game
	 *
	 * @param String sentence - The sentence we want to play Hangman with
	 */
    public Hangman(String sentence) {
        setPhrase(sentence);
    }
    
	/** 
	 * Adds a guess into the game, the @param guess can be a full word, multi-word
	 * or it can be a string with a single character. The function witll automatically 
	 * decided what type of guess it is and dispatch the proper function to handle the
	 * guess. 
	 *
	 * @param String guess - The String that we want to guess
	 */
    public HangmanStatus addGuess(String guess) {
		if (guess.length() > 1) {
			return mLastStatus = tryString(guess.toLowerCase());
		} else {
			return mLastStatus = tryChar(Character.toLowerCase(guess.charAt(0)));
		}
	}
    
	/** 
	 * Resets the Hangman game with a new string. 
	 *
	 * @param String sentence - The new sentence we want to play Hangman with
	 */
    public void setPhrase(String sentence) {
		sentence = " " + sentence + " ";
        mSentence = new String(sentence);
		// there has to be a more elegant way to do this
		mGuessedSentence = new StringBuilder(sentence);
		for (int ii = 0; ii < mGuessedSentence.length(); ++ii) {
			mGuessedSentence.replace(ii, ii + 1, "_");
		}
        mGuessedLetters = new StringBuilder(""); // empty
		mGuessedWords = new java.util.Vector<String>();
        mNumMissed = 0;
		
		// we want anything that isnt a letter to be freebees
		for (int ii = 0; ii < mGuessedSentence.length(); ++ii) {
			char ch = mSentence.charAt(ii);
            if (!Character.isLetter(ch)) mGuessedSentence.replace(ii, ii + 1, Character.toString(ch));
        }
    }

	/**
	 * This function returns the String representation of the class. 
	 * The basic format it this: ASCII image of the man, guessed string,
	 * characters we have guessed, then words/strings we have guessed. 
	 * EX: 
	 * 
	 *
	 * @return String - The string representation of the object
	 */
    public String toString() {
        return
        numGuess2str(mNumMissed) + "\n" +
        "Guessed Sentence: " + mGuessedSentence + "\n" +
        "Guessed Letters : " + mGuessedLetters + "\n" +
		"Guessed Words   : " + getGuessedWordsString() + "\n";
    }

	/**
	 * Returns the full String guesses (words/multi-word) as a
	 * CSV (comma seperated values) 
	 *
	 * @return String - The strings we have guessed as a String
	 */
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
	
	/**
	 * Converts the number of missed guesses into the body-parts on the Hangman 
	 *
	 * @return String - A String containing the ASCII Hangman image
	 */
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
    
	/**
	 * Attempts to guess a single character. The character MUST be lowercase
	 * The function is case-insensitive, if we try to guess a lowercase a,
	 * It will automatically guess both an UPPERCASE A and an lowercase a.
	 * It will also use the chars from the orignal string so the replaced
	 * character will have the same case as the original string. 
	 * The function will automatically will check if we have guessed the 
	 * letter before. 
	 *
	 * @param char guess - The character we want to guess
	 */
	private HangmanStatus tryChar(char guess) {
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
	
	/**
	 * Attempts to guess a full string, the string can contain space and
	 * will count as a multi-word guess. The guess is case-insensitive 
	 * This function also double checks if we have guessed the string 
	 * before. Similar to @sa HangmanStatus tryChar(char), this function
	 * uses the original string to replace the @c{mGuessedSentence} to 
	 * preserve the case from the original string. 
	 *
	 * @param String str - The string we want to guess
	 */
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

	// ============ Unused accessors functions ================================
	
	/**
	 * Returns the original string we are using for the Hangman game
	 *
	 * @return String - The original string for the game
	 */
    public String getSentence() {
        return mSentence;
    }

	/**
	 * Returns the characters we have already guessed as a string
	 *
	 * @return String - The guessed characters as a string
	 */    
    public String getGuessesChars() {
        return mGuessedLetters.toString();
    }
    
	/**
	 * Returns the parts of the string we have already guessed correct
	 *
	 * @return String - The parts of the string we have guessed correct
	 */ 
    public String getGessedSentence() {
        return mGuessedSentence.toString();
    }
    
	// ============ Functions for legacy use =====================
	// does Java have [[depreciated]] tag?
	
	/**
	 * Returns if we have lost the game or not. This function exists because of
	 * the requirements of the assignment
	 */
	public boolean getGameOver() {
		return mLastStatus == HangmanStatus.GUESS_LOSE;
	}

	/**
	 * Tries to guess the character. Stores the state in mLastStatus. 
	 *
	 * @param char symbol - The char we want to guess
	 */
	public void find(char symbol) {
		mLastStatus = tryChar(Character.toLowerCase(symbol));
	}
	
	/**
	 * Returns if we have won or not.
	 * 
	 * @return boolean - If we have won the game or not
	 */
	public boolean checkWin() {
		return mLastStatus == HangmanStatus.GUESS_WIN;
	}
    
	/**
	 * Returns the last status of the game.
	 *
	 * @return HangmanStatus - The status of the last guess
	 */
	public HangmanStatus getLastStatus() {
		return mLastStatus;
	}
	
	// =========== End Legacy functions ========================================
}
