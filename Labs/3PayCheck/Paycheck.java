
/**
 * Write a description of class Paycheck here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Paycheck
{
    private String mName;
	private double mPay;
	private double mHoursWorked;
	private double mGrossPay;
	
	public Paycheck() {
		mName = "Unknown";
		mPay = 0.0;
		mHoursWorked = 0.0;
		mGrossPay = 0.0;
	}
	
	public Paycheck(String name, double pay, double hw) {		
		mName = name;
		mPay = pay;
		mHoursWorked = hw;
		calculateGrossPay();
	}
	
	public String getName() {
		return mName;
	}
	
	public double getGrossPay() {
		return mGrossPay;
	}
	
	public void setName(String name) {
		mName = name;
	}
	
	public void setHoursWorked(double hw) {
		mHoursWorked = hw;
		calculateGrossPay();
	}
	
	public void setHourlyRate(double rate) {
		mPay = rate;
		calculateGrossPay();
	}
	
	public double getHoursWorked() {
		return mHoursWorked;
	}
	
	public double getHourlyRate() {
		return mPay;
	}
	
	private void calculateGrossPay() {
		/// @note remember to calculate overpay if over 40 hours
		mGrossPay = 0.0;
		if (mHoursWorked > 40) {
			double extraHoursWorked = mHoursWorked - 40;
			mGrossPay += extraHoursWorked * (mPay * 1.5);
			mGrossPay += mPay * 40;
			return;
		}
		
		mGrossPay += mPay * mHoursWorked;
	}
	
	public String toString() {
		return
		"Employee: " + mName + "\n" +
		"Hours Worked: " + mHoursWorked + "\n" +
		"Rate of Pay: " + mPay + "\n" +
		"Gross Pay: $" + mGrossPay + "\n";
	}
}
