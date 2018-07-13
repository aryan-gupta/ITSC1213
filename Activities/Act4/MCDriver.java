
/**
 * Drives the MileConverter class, asks user if s/he wants
 * to convert from m2f or f2m, then converts it. Does
 * basic error checking and exception catching. 
 *
 * @author Aryan Gupta
 * @version 1.0
 */
import java.util.Scanner;
import java.util.InputMismatchException;
import static java.lang.System.out;

public class MCDriver
{
    public static void main() {
		Scanner in = new Scanner(System.in);
		MileageConverter mc = new MileageConverter();
		do {
			out.println("Would you like to convert: ");
			out.println("1) From miles to feet");
			out.println("2) From feet to miles");
			
			double ans = 0.0;
			do {
				try {
					out.print("Type -1, 1 or 2:: ");
					ans = in.nextDouble();
					// check is ans is -1, 1, 2
					if (ans > 2 || ans < -1) { // maybe use Double.compare()?
						out.println("Wrong choice!");
						continue;
					}
					break; // leave if ans is valid
				} catch (InputMismatchException e) {
					out.println("Wrong choice!");
					// Honestly dont remember why I need this but
					// I remember from my java days that there is
					// somthing left in the buffer and we need to 
					// clean it before we can move on.
					// maybe the incorrect value that we caught here
					// isnt remved from the buffer? idk
					String dummy = in.next();
				}
			} while (true);
			
			if (Double.compare(ans, -1.0) == 0) {
				break;
			}
			
			double len = 0.0;
			do {
				try {
					out.println("Please enter the length you would like to convert");
					out.print(":: ");
					len = in.nextDouble();
					break; // leave is len is valid
				} catch (InputMismatchException e) {
					out.println("Wrong!");
					String dummy = in.next();
				}
			} while (true);
			
			if (len < 0) { // negatives make us exit
				break;
			}
			
			if (Double.compare(ans, 1.0) == 0) {
				mc.setMiles(len);
			} else { // ans == 2
				mc.setFeet(len);
			}
			
			out.println(mc);
			
		} while(true);
		
		out.println("Thank you for playing!");
	}
}
