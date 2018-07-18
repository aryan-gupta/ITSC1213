
/**
 * Implements a ASCII rotate right class. Converts 'abc' to
 * 'bcd', Similar to Caesar Cypher of +1.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ASCIIShifter
{
	private String mOriginalString; // stores the original string
    private StringBuilder mString; // stores the string while we modify it
	
	/** 
	 *	This is the default constructor for this class, initializes the class
	 *	with NULL, could do empty string, but that is ultimately a design
	 *	choice decided by the programmer
	 */
	public ASCIIShifter() {
		setString("NULL"); // initialize with empty string
	}
	
	/**
	 *	This constructor takes in a string that must be converted with an ASCII
	 *	shift (similar to Caesar Cypher). See the {@link #convert() convert}
	 *	meathod for more info. Calls {@link #setString() setString} to reduce
	 *	code redundancy
	 *
	 *	@p String str - The string that we want to convert
	 */
	public ASCIIShifter(String str) {
		setString(str);
	}
	
	/** 
	 *	This function returns the original string.
	 *
	 *	@return String - The original string
	 */
	public String getOriginalString() {
		return mOriginalString;
	}

	/** 
	 *	This function returns the converted string. See {@link #convert() convert} for
	 *	more information about this conversion. 
	 *
	 *	@return String - The converted string
	 */
	public String getConvertedString() {
		return mString.toString();
	}
	
	/** 
	 *	This function returns sets the string to be converted, then converts the string.
	 *	See {@link #convert() convert} for more information about this conversion. 
	 *
	 *	@p String str - The string to be converted
	 */
	public void setString(String str) {
		mOriginalString = new String(str);
		mString = new StringBuilder(str);
		convert();
	}
	
	/** 
	 *	This function converts our class into a string 
	 *
	 *	@p String str - The string representation of this class
	 */	
	public String toString() {
		return "\"" + mOriginalString + "\" converted is \"" + mString + "\"";
	}
	
	/* 
	 *	This function converts the original string to the new string. The conversion is 
	 *	a ASCII rotate right. 'a' becomes 'b' and 'b' becomes 'c'. Similar to the 
	 *	Caesar Cypher of +1. 
	 */
	private void convert() {
		for (int ii = 0; ii < mString.length(); ++ii) {
			// gets the char at ii then adds one to it then sets
			// that char to the incremented character. 
			/// @warning this does not loop over (z does not loop
			/// over to A and vise versa).
			// If we wanted to fix the warning, then we should use
			// a String of all thw characters like 'abc...ABC...' 
			// and use it as a hashmap/map
			mString.setCharAt(ii, (char)(mString.charAt(ii) + 1));
		}
	}
}
