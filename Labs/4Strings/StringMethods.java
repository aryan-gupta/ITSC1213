
/**
 * This class contains a main( ) method only
 * and its purpose it to demonstrate the use
 * of several methods in the String class API
 * 
 * Each team member must submit the lines of code on paper 
 * and each team must submit their completed code.
 * 
 * @author your team name 
 * @version todays's date
 */
public class StringMethods
{
    public static void main(String[ ] args)
    {
        String sentence;
        int count;
        
        sentence = new String("The Mississippi originates in Minnesota and flows to Louisiana.");
        System.out.println("The number of characters in the sentence:\n" + sentence + "\nis "+ sentence.length());
        count = 0;
        for(int k = 0; k < sentence.length( ); k++)
        {
            if(sentence.charAt(k) == 'i')
            {
                count++;
            }
        }
        System.out.println("The lowercase i appears " + count + " times.");
      //todo using the String class API methods
	  System.out.println("Contains 'nat': " + (sentence.indexOf("nat") != -1 ? "true" : "false"));
	  System.out.println("Contains 'beat': " + (sentence.indexOf("beat") != -1 ? "true" : "false"));
	  System.out.println("Ends with 'ana': " + (sentence.endsWith("ana") ? "true" : "false"));
	  System.out.println("Ends with 'ana.': " + (sentence.endsWith("ana.") ? "true" : "false"));
	  int idx = sentence.indexOf("s");
	  System.out.println("First 's': " + idx++);
	  idx = sentence.indexOf("s", idx);
	  System.out.println("Second 's': " + idx++);
	  idx = sentence.indexOf("s", idx);
	  System.out.println("Third 's': " + idx++);
	  System.out.println("Last 's': " + (sentence.lastIndexOf("s")));
	  System.out.println("First 's': " + (sentence.indexOf("is")));
	  System.out.println("Last 'is': " + (sentence.lastIndexOf("is")));
	  System.out.println("Replace 's' with 'z': " + (sentence.replace("s", "z")));
	  System.out.println("Substring: " + (sentence.substring(3, 3 + 6)));
	  System.out.println("Upper: " + (sentence.toUpperCase()));
	  
      /* 1) Does the sentence contain the string "nat" ?
       * 2) Does the sentence contain the string "beat" ?
       * 3) Does the sentence end with "ana"?
       * 4) Does the sentence end with "ana."?
       * 5) Where is the first occurace of 's' ?
       * 6) Where is the second occurance of 's'?
       * 7) Where is the third occurance of 's'?
       * 8) Where is the last occurace of 's'?
       * 9) Where does the  first occurance of "is" start?
       * 10)Where does the last occurance of "is" start"?
       * 11)Replace each occurance of 's' with 'z'.
       * 12) Does step 11 change the original sentence, prove it by printing.
       * 13) Print the substring of the sentence beginning at character 3 for 6 characters"
       * 14) Print the entire sentence in upper case.
       * 15) Does step 13 change the original string, prove it by printing
       */
    
    
    }
}