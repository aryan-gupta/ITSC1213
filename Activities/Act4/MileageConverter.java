
/**
 * This class converts from miles to feet or feet to miles
 *
 * @author Aryan Gupta
 * @version 1.0
 */

// https://docs.oracle.com/javase/7/docs/api/java/security/InvalidParameterException.html
import java.security.InvalidParameterException;

public class MileageConverter
{
	public enum TYPES {
		FEET, MILE, INVALID
	}
	
	// https://docs.oracle.com/javase/8/docs/technotes/guides/language/underscores-literals.html
    private final double MI2FT = 5_280;
	
	private double mFeet;
	private double mMiles;
	
	public MileageConverter() {
		mFeet = 0.0;
		mMiles = 0.0;
	}
	
	public MileageConverter(TYPES type, double length) {
		switch (type) {
			case FEET:
				mFeet = length;
			break;
			
			case MILE:
				mMiles = length;
			break;
			
			case INVALID:
			default:
				// https://stackoverflow.com/questions/6086334/is-it-good-practice-to-make-the-constructor-throw-an-exception
				throw new InvalidParameterException();
		}
		
		update(type);
	}
	
	public MileageConverter(double length, char type) {
		switch (type) {
			case 'F':
			case 'f':
				mFeet = length;
				update(TYPES.FEET);
			break;
			
			case 'M':
			case 'm':
				mMiles = length;
				update(TYPES.MILE);
			break;
			
			default:
				throw new InvalidParameterException();
		}
	}
	
	public double getMiles() {
		return mMiles;
	}
	
	public double getFeet() {
		return mFeet;
	}
	
	public void setMiles(double miles) {
		mMiles = miles;
		update(TYPES.MILE);
	}
	
	public void setFeet(double feet) {
		mFeet = feet;
		update(TYPES.FEET);
	}
	
	public String toString() {
		return 
		mFeet + " ft in miles is " +
		mMiles + " mi";
	}
	
	private void update(TYPES type) {
		// uses type as a reference to update the other 
		// value
		switch (type) {
			case FEET:
				mMiles = mFeet / MI2FT;
			case MILE:
				mFeet = mMiles * MI2FT;
			// type will aways be a valid value (hence its private access)
		}
	}
}
