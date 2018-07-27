
/**
 * Write a description of class Reader here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner;
import java.io.File;
public class Reader
{
    public static void main() throws java.io.IOException {
        Scanner fileIn = new Scanner(new File("data.dat"));
        
        while (fileIn.hasNext()) {
            System.out.println(fileIn.nextLine());
        }
        
        fileIn.close();
    }
}
