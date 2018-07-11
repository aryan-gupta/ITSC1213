
/**
 * Converts too and from different currencies
 *
 * @author Aryan Gupta
 * @version 1.0
 */
public class Currency
{
	// https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
	// also turns out you can define a class within class in Java
	// we want this public so we can pass it in to constructor
	// Also Java doest allow enum as index so we cant
	// store converted amounts as an array :(
	// https://stackoverflow.com/questions/5417454/use-enums-for-array-indexes-in-java
	public enum EX_TYPES {
		USD, EUR, GBP, INR, AUD, CAD, INVALID
	}
	
    private final double USD2EUR;
    private final double USD2GBP;
    private final double USD2INR;
    private final double USD2AUD;
    private final double USD2CAD;
	
	private double dollar;
	private double euro;
    private double pound; 
    private double rupee;
    private double adoll; 
    private double loony;
	
	public Currency() {	
		
		USD2EUR = 0.853707;
		USD2GBP = 0.754565;
		USD2INR = 68.779561;
		USD2AUD = 1.3519;
		USD2CAD = 1.315081;
		
		dollar = euro = pound = rupee = adoll = loony = 0;
	}
	
	public Currency(EX_TYPES inType, double amnt) {
		
		USD2EUR = 0.853707;
		USD2GBP = 0.754565;
		USD2INR = 68.779561;
		USD2AUD = 1.3519;
		USD2CAD = 1.315081;
		
		setAmount(inType, amnt);
	}
	
	public void setAmount(EX_TYPES inType, double amnt) {
		switch (inType) {
			case USD:
				dollar = amnt;
			break;
			
			case EUR:
				euro = amnt;
			break;
			
			case GBP:
				pound = amnt;
			break;
			
			case INR:
				rupee = amnt;
			break;
			
			case AUD:
				adoll = amnt;
			break;
			
			case CAD:
				loony = amnt;
			break;
		}
		update(inType);
	}
	
	public double getAmount(EX_TYPES inType, double amnt) {
		switch (inType) {
			case USD: return dollar;
			case EUR: return euro;
			case GBP: return pound;
			case INR: return rupee;
			case AUD: return loony;
			case CAD: return amnt;
		}
		// https://stackoverflow.com/questions/7419241/can-float-or-double-be-set-to-nan
		// could throw but then need to handle it
		return Float.NaN;
	}
	
	// for the lack of a better name
	public static EX_TYPES str2type(String str) {
		// so weird using switch with a string
		switch (str) {
			case "USD": return EX_TYPES.USD;
			case "EUR": return EX_TYPES.EUR;
			case "GBP": return EX_TYPES.GBP;
			case "INR": return EX_TYPES.INR;
			case "AUD": return EX_TYPES.AUD;
			case "CAD": return EX_TYPES.CAD;
		}
		// Why does Java yell at me that there is no return
		// type if I use default
		return EX_TYPES.INVALID;
	}
	
	// Java doesnt allow this to be called in the constructor
	// private void setExchangeRates() { 
		// USD2EUR = 0.853707;
		// USD2GBP = 0.754565;
		// USD2INR = 68.779561;
		// USD2AUD = 1.3519;
		// USD2CAD = 1.315081;
	// }
	
	/// Question: Why am I writing so many get/set meathods?
	public double getUSD() {
		return dollar;
	}
	
	public double getEUR() {
		return euro;
	}
	
	public double getGBP() {
		return pound;
	}
	
	public double getINR() {
		return rupee;
	}
	
	public double getAUD() {
		return adoll;
	}
	
	public double getCAD() {
		return loony;
	}
	
	public void setUSD(double amnt) {
		dollar = amnt;
		update(EX_TYPES.USD);
	}
	
	public void setEUR(double amnt) {
		euro = amnt;
		update(EX_TYPES.EUR);
	}
	
	public void setGBP(double amnt) {
		pound = amnt;
		update(EX_TYPES.GBP);
	}
	
	public void setINR(double amnt) {
		rupee = amnt;
		update(EX_TYPES.INR);
	}
	
	public void setAUD(double amnt) {
		adoll = amnt;
		update(EX_TYPES.AUD);
	}
	
	public void setCAD(double amnt) {
		loony = amnt;
		update(EX_TYPES.CAD);
	}
	
	/// Updates all the values using @p type as the reference
	private void update(EX_TYPES type) {
		// This will set the reference currency using the
		// input currency we specify and then recaluclate
		// the amount in the different types
		switch (type) {
			// case USD:
				// dollar = dollar; // idk why, but ya...
			// break;
			
			case EUR:
				dollar = euro / USD2EUR;
			break;
			
			case GBP:
				dollar = pound / USD2GBP;
			break;
			
			case INR:
				dollar = rupee / USD2INR;
			break;
			
			case AUD:
				dollar = adoll / USD2AUD;
			break;
			
			case CAD:
				dollar = loony / USD2CAD;
			break;
			
			default: // fall through for USD (we are using dollar as the reference currnency)
		}
		
		/// @note one of these will alwaus be recaluclated. 
		/// we can copy this into each case statement if we are 
		/// worried about performance (performance vs size)
		
		euro  = dollar * USD2EUR;
		pound = dollar * USD2GBP;
		rupee = dollar * USD2INR;
		adoll = dollar * USD2AUD;
		loony = dollar * USD2CAD;
	}
	
	public String toString() {
		return
		"Dollars    : " + dollar + "\n" +
		"Euros      : " + euro   + "\n" +
		"Pounds     : " + pound  + "\n" +
		"Rupees     : " + rupee  + "\n" +
		"AUS Dollars: " + adoll  + "\n" +
		"Loonies    : " + loony  + "\n";
		
	}
}
