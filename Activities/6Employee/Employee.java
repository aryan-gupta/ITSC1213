
/**
 * This class holds fields and methods for an hourly employee
 *
 * @author Aryan Gupta
 * @version 1.0
 */
public class Employee
{
	public enum PerformanceGrade {
		UNKNOWN, POOR, FAIR, GOOD, EXCEL
	}
	
	// Fields are declared outside any member methods
	// usually given private access
	// uses camelCase convension
    private final String mSTART_DATE;
	
	private String mFirstName;
    private String mLastName;
	private String mSSN; // probs should create a class specifically for this
	private double mHourlyWage;
	private PerformanceGrade mLastRating;
	
	// c'tor only called once for each instance if this class
	// inilizes fields to initial values
	// must be the same name as class
	// no return type
	public Employee() {
		mSTART_DATE = ""; // huh? Its a final, cannot be changes anymore oops
		mFirstName = "unknown";
		mLastName = "unknown";
		mSSN = "000-000-000";
		mHourlyWage = 0.00;
		mLastRating = UNKNOWN;
	}
	
	// you can overload the c'tor
	public Employee(String first, String last, String ssn, double rate, PerformanceGrade grade, String start) {
		mSTART_DATE = start;
		mFirstName = first;
		mLastName = last;
		mSSN = ssn;
		mHourlyWage = rate;
		mLastRating = grade;
		// prevent 'something somthing shadows a parameter' error
		// this.parameterName = parameterName
	}
	
	// set*() functions are to set fields after object is
	// created
	
	public void setHourlyWage(double wage) {
		if (wage < 7.5) return;
		
		mHourlyWage = wage;
	}
	
	public double getHourlyWage() {
		return mHourlyWage;
	}
	
	public void setPerformanceGrade(PerformanceGrade grade) {
		mLastRating = grade;
		calculateRaise();
	}
	
	public PerformanceGrade getPerformanceGrade() {
		return mLastRating;
	}
	
	// provate methods can only be called within the class
	private void calculateRaise() {
		switch (mLastRating) {
			case EXCEL:
				mHourlyWage *= 1.10;
			break;
			
			case GOOD:
				mHourlyWage *= 1.07;
			break;
			
			case FAIR:
				mHourlyWage *= 1.02;
			break;
			
			default:
		}
	}
	
	public double calculatePay(double hours) {
		double pay;
		
		if (hours <= 40) {
			pay = hours * mHourlyWage;
		} else {
			pay = (40 * mHourlyWage) + ((1.5 * mHourlyWage) * (hours - 40));
		}
	}
	
	// the toString() method is used to return the string 
	// representation of the class
	public String toString() {
		return "I am an Employee";
	}
}
