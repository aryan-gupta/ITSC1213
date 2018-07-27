
/**
 * Write a description of class EmployeeDriver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EmployeeDriver
{
    public static void main() {
		Employee e1 = new Employee (
			"Aryan", "Gupta",
			"123-456-789",
			8.9,
			Employee.PerformanceGrade.GOOD,
			"7/18/2018"
		);
		
		e1.setHourlyPay(13.2); // I wish
		System.out.println(e1);
	}
}
