
import java.io.*;
import java.util.*;

public class CMDLine {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("Usage: java CMDLine <file1> <file2>");
			return;
		}
		
		Scanner inFile;
		
		try {
			inFile = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			System.err.println("Error: File not found '" + args[1] + "'");
			return;
		}
		
		PrintWriter outFile;
		try {
			outFile = new PrintWriter(args[1]);
		} catch (FileNotFoundException e) {
			System.err.println("Error: File not found '" + args[1] + "'");
			return;
		}
		
		while (inFile.hasNext()) {
			outFile.println(inFile.nextLine());
		}
		
		inFile.close();
		outFile.close();
	}
}