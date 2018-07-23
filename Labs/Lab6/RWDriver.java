
/**
 * Write a description of class RWDriver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.io.File;

public class RWDriver
{
    public static void main() throws java.io.IOException {
        Scanner fileIn = new Scanner(new File("data.dat"));
        
        int numEs = 0;
        int numVowels = 0;
        int numChars = 0;
        int numSpaces = 0;
        
        while (fileIn.hasNext()) {
            String myString = fileIn.nextLine();
            System.out.println(myString.toUpperCase());
            numEs += (myString.toLowerCase().length() - myString.toLowerCase().replace("e", "").length());
            numVowels += (myString.toLowerCase().length() - myString.toLowerCase().replace("a", "").replace("e", "").replace("i", "").replace("o", "").replace("u", "").length());
            numChars += myString.length();
            numSpaces += (myString.toLowerCase().length() - myString.toLowerCase().replace(" ", "").length());
            
        }
        
        System.out.println("Number of e's   : " + numEs);
        System.out.println("Number of vowels: " + numVowels);
        System.out.println("Number of chars : " + numChars);
        System.out.println("Number of spaces: " + numSpaces);
        
        fileIn.close();   
    }
}
