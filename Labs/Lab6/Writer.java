
/**
 * Write a description of class Writer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.PrintWriter;
import java.util.Scanner;

public class Writer
{
    public static void main() throws java.io.IOException {
        PrintWriter outFile = new PrintWriter("out.dat");
        Scanner in = new Scanner(System.in);
        int lines = 7;
        
        while (lines --> 0) {
            System.out.print("Enter a sentence:: ");
            String str = in.nextLine();
            outFile.println(str);
        }
        outFile.close();
    }
}
