

import java.util.LinkedHashMap;
/**
 * Uses the java.util.StringTokenizer to tokenize strings using 1 or multiple 
 * delimiter. Strings are inputted from stdin or from a file
 *
 * @author Aryan Gupta
 * @version 1.0
 */

import java.util.Map;
import java.util.Scanner;
import java.io.File;

public class StringTokenizerMain {	
	/**
	 * Program Entry
	 *
	 * @param String[] args - command line parameters
	 */
	public static void main(String[] args) throws java.io.FileNotFoundException {
		Scanner in = new Scanner(System.in);
		
		// https://docs.oracle.com/javase/7/docs/api/java/util/LinkedHashMap.html
		// not needed but curious about Java's interface
		//LinkedHashMap<String, String> delims = Map.of("space", " ", "coma", ",", "c&s", " ,"); // NEED JAVA 9 MIN
		LinkedHashMap<String, String> delims = new LinkedHashMap<>();
		// Where is my initializer list?????
		delims.put("space", " ");
		delims.put("coma", ",");
		delims.put("comma or space", " ,");
		
		
		// https://www.geeksforgeeks.org/iterate-map-java/
		// Not having auto is mildly annoying, especially cause BlueJ doesnt do autocomplete
		for (Map.Entry<String, String> delim : delims.entrySet()) {
			System.out.print("Please enter strings separated by a " + delim.getKey() + ":: ");
			String str = in.nextLine();
			
			java.util.StringTokenizer st = new java.util.StringTokenizer(str, delim.getValue());
		
			int numTokens = st.countTokens();
			// Alright, so we want to go through each of the delim characters and 
			// remove them from the string we had then we want to get the difference
			// of the length. This will give us how many chars we removed, which is 
			// equal to the number of delims. The reason Im using this method because
			// doing countTokens() - 1 will be wrong if we have 2 delims next to each
			// other. For example, Word1,,Word2 is 2 delims and 2 tokens. Using
			// countTokens() would give wrong value of 1 delims and 2 tokens
			String strNoDelim = new String(str);
			for (char c : delim.getValue().toCharArray()) {
				strNoDelim = strNoDelim.replace(Character.toString(c), "");
			}
			int numDelim = str.length() - strNoDelim.length();
			
			System.out.println("Num of tokens: " + numTokens);
			System.out.println("Tokens: ");
			while (st.hasMoreTokens()) { System.out.println('\t' + st.nextToken()); }
			System.out.println("Num of tokens + delim: " + (numDelim + numTokens));
			System.out.println();
		}
		
		// too lazy to catch, ill just declare main throws io exception
		// (I could have added a try catch in the time it took me to write this comment)
		Scanner file = new Scanner(new File("test.txt"));
		int numTotalTokens = 0;
		while (file.hasNext()) {
			String str = file.nextLine();
			java.util.StringTokenizer st = new java.util.StringTokenizer(str, ",");
			int numTokens = st.countTokens();
			numTotalTokens += numTokens;
			System.out.println("Num of tokens: " + numTokens);
		}
		
		System.out.println("Num of total tokens: " + numTotalTokens);
	}
}