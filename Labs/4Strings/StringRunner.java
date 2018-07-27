
/**
 * Write a description of class StringBuilder here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;

public class StringRunner
{
	public static void main() {
		Scanner in = new Scanner(System.in);
		String myString = new String();
		
		System.out.println("Enter a string:: ");
		//myString = "Hello my name is Aryan, and I love 413";
		myString = in.nextLine();
		
		System.out.println("Length: "              + myString.length());
		System.out.println("First char: "          + myString.charAt(0));
		System.out.println("Last char: "           + myString.charAt(myString.length() - 1));
		System.out.println("contains 'e': "        + (myString.indexOf('e') != -1 ? "true" : "false"));
		System.out.println("contains 'ay': "       + (myString.indexOf("ay") != -1 ? "true" : "false"));
		System.out.println("Num of 'e': "          + (myString.length() - myString.replace("e", "").length()));
		System.out.println("Last index of 'e': "   + myString.lastIndexOf('e'));
		int first = myString.indexOf('e');
		System.out.println("Second index of 'e': " + myString.indexOf('e', ++first));
		System.out.println("Num an chars: "        + (myString.replace(" ", "").length()));
		System.out.println("cact: "                + myString + " you know");
		System.out.println("Upper: "               + myString.toUpperCase());
		System.out.println("substring: "           + myString.substring(0, 5));
		System.out.println("replace 'a': "         + myString.replace("a", ""));
	}
}
