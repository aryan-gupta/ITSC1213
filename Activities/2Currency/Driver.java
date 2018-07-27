
/**
 * A simple driver program for the currency class
 * Asks for amount of money and the type of currency
 * Basic error checking and handling
 *
 * @author (your name)
 * @version (a version number or a date)
 */
 
// https://stackoverflow.com/questions/6688017/how-do-you-shorten-system-out-println-in-java
import static java.lang.System.out;
import java.util.Scanner;
 
public class Driver
{
    public static void main() {
		System.out.print('\u000C');
		Scanner sc = new Scanner(System.in);
		Currency money = new Currency();
		boolean exit = false;
		do {
			boolean valid = false;
			double amnt = 0.0;
			Currency.EX_TYPES enumType = Currency.EX_TYPES.INVALID;
			do {
				out.println("Tell me how much you got?");
				out.println(":: ");
				String amntstr = sc.next();
				if (amntstr.toLowerCase().equals("exit")) {
					exit = true;
					break;
				}
				try {
					amnt = Double.parseDouble(amntstr);
					valid = true;
				} catch (java.lang.NumberFormatException e) {
					out.println("Man, ain't nobody got time for that");
				}
			} while (!valid);
			
			if (exit) break;
			
			do {
				out.println("What type is it?");
				out.println("Valid options are USD, EUR, GBP, INR, AUD, and CAD");
				out.println(":: ");
				String type = sc.next();
				if (type.toLowerCase().equals("exit")) {
					exit = true;
					break;
				}
				enumType = Currency.str2type(type);
				if (enumType == Currency.EX_TYPES.INVALID) {
					out.println("Man, ain't nobody got time for that");
				} else {
					break;
				}
			} while (true); // loops till we get a valid currency type
			
			if (exit) break;
			
			money.setAmount(enumType, amnt);
			out.println("Your goods are worth these much: ");
			System.out.println(money); // toString is auto called
		} while (!exit); // loops till we type in exit at any of our input points
		
		out.println("Goodbye!");
	}
	
	
}
