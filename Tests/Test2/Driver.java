
/**
 * A simple test program for the Pet class
 * Loads 6 pets from a file and runs each method
 * as a test
 *
 * @author Aryan Gupta
 * @version 1.0
 */
import java.util.Scanner;
import java.util.StringTokenizer;

public class Driver
{
    /**
     * Method main - Program entry point
     *
     * @param String[] args - The comand line arguments
     */
    public static void main(String[] args) {
        int numOfPets = 6;
        Pet[] pets = new Pet[numOfPets];
        
        Scanner file;
        try {
            file = new Scanner(new java.io.File("rescue.txt"));
        } catch (java.io.IOException e) {
            System.err.println("Somthing went wrong....");
            return;
        }
        int idx = -1;
        while (file.hasNext() && ++idx < numOfPets) {
            StringTokenizer st = new StringTokenizer(file.nextLine(), ",");
            // growing array similar to std::vector from c++
            java.util.Vector<String> tokens = new java.util.Vector();
            while (st.hasMoreTokens()) {
                tokens.add(st.nextToken());
            }
            String type = tokens.elementAt(0);
            String name = tokens.elementAt(1);
            double cost = Double.parseDouble(tokens.elementAt(2));
            pets[idx] = new Pet(type, name, cost);
        }
        
        for (Pet p : pets) {
            System.out.println(p);
        }
        
        System.out.println(pets[0].equals(pets[1])? "The first and second pets are the same" : "The first and second pets are different");
        
        int cmp = pets[0].compareTo(pets[numOfPets - 1]);
        if      (cmp > 0)
            System.out.println("First Pet costs more than the last one");
        else if (cmp < 0)
            System.out.println("Last Pet costs more than the first one");
        else
            System.out.println("They first and last pet cost the same");
    }
}
