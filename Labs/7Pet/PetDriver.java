
/**
 * Write a description of class PetDriver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner;
import java.util.StringTokenizer;
public class PetDriver
{
    public static void main() throws java.io.IOException {
        // growing array similar to std::vector from c++
        java.util.Vector<Pet> pets = new java.util.Vector();
        
        Scanner file = new Scanner(new java.io.File("rescue.txt"));
        int idx = 0;
        while (file.hasNext()) {
            StringTokenizer st = new StringTokenizer(file.nextLine(), ",");
            java.util.Vector<String> tokens = new java.util.Vector();
            while (st.hasMoreTokens()) {
                tokens.add(st.nextToken());
            }
            String type = tokens.elementAt(0);
            String name = tokens.elementAt(1);
            double cost = Double.parseDouble(tokens.elementAt(2));
            pets.add(new Pet(type, name, cost));
        }
        
        for (Pet p : pets) {
            System.out.println(p);
        }
        
        System.out.println(pets.elementAt(0).equals(pets.elementAt(1)));
        System.out.println(pets.elementAt(0).compareTo(pets.elementAt(2)));
    }
}
