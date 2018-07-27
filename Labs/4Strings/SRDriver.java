
/**
 * Write a description of class SRDriver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
 
import java.util.Scanner;

public class SRDriver
{
    public static void main() {
		Scanner in = new Scanner(System.in);
		StringReplacer st = new StringReplacer("");
		
		do {
			System.out.print("Enter a String:: ");
			String org = in.nextLine();
			if (org.compareTo("exit") == 0) break;
			st.setString(org);
			System.out.print("Enter a String to be replace:: ");
			String a = in.nextLine();
			if (a.compareTo("exit") == 0) break;
			System.out.print("Enter a String to replace it with:: ");
			String b = in.nextLine();
			if (b.compareTo("exit") == 0) break;
			
			st.replaceThis(a, b);
			
			System.out.println("Org String: " + st.getOriginalString());
			System.out.println("New String: " + st.getNewString());
		} while (true);
		
		System.out.println("Good Bye!");
	}
}
