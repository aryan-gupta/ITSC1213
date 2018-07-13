
/**
 * Replaces 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StringReplacer
{
    private String mStr;
	private StringBuilder mNewStr;
	
	public StringReplacer(String str) {
		setString(str);
	}
	
	/// Replaces ever occurance replacer with replacee
	public void replaceThis(String replacee, String replacer) {
		int start = 0;
		do {
			start = mNewStr.indexOf(replacee, start);
			if (start == -1) break;
			mNewStr = mNewStr.replace(start, start + replacee.length(), replacer);
			++start;
		} while (true);
	}
	
	public void setString(String str) {
		mNewStr = new StringBuilder(str);
		mStr = str;
	}
	
	public String getOriginalString() {
		return mStr;
	}
	
	public String getNewString() {
		return mNewStr.toString();
	}
}
