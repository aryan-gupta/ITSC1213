
/**
 * Write a description of class PayDriver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
 
 import java.util.*;
 
public class PayDriver
{
    public static void main() {
		Scanner input = new Scanner(System.in);
		// while (true) {
			System.out.print("Enter Name:: ");
			String name = input.next();
			System.out.print("Enter Pay:: ");
			double pay = input.nextDouble();
			System.out.print("Enter Hours:: ");
			double hours = input.nextDouble();
			
			Paycheck check = new Paycheck(name, pay, hours);
			
			System.out.print(check);
			
			System.out.print("Enter new name:: ");
			name = input.next();
			check.setName(name);
			System.out.print(check);
			System.out.print("\n");
			
			System.out.print("Enter new pay:: ");
			pay = input.nextDouble();
			check.setHourlyRate(pay);
			System.out.print(check);
			System.out.print("\n");
			
			System.out.print("Enter new hours:: ");
			hours = input.nextDouble();
			check.setHoursWorked(hours);
			System.out.print(check);
			System.out.print("\n");
			
		// }
	}
}
