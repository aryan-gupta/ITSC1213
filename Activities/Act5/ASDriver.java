
/**
 * Write a description of class ASDriver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.io.*;

public class ASDriver
{
    public static void main() throws IOException {
		Scanner fileIn = new Scanner(new File("data.txt"));
		PrintWriter fileOut = new PrintWriter("out.txt");
		ASCIIShifter converter = new ASCIIShifter();
		
		while (fileIn.hasNext()) {
			converter.setString(fileIn.nextLine());
			fileOut.println(converter.getConvertedString());			
		}
		fileIn.close();
		fileOut.close();
	}
}
