
/**
 * Write a description of class RandomArray here.
 * Creates a array filled with random digits and then counts the number of 
 * them that are odds and number that are evens
 *
 * @author Aryan Gupta
 * @version 1.0
 */

import java.util.Random;

public class RandomArray
{   
    private static final int NUMS = 15;
    public static void CreateRandomArray() {
        Random rand = new Random();
        
        int[] randArray = new int[NUMS];
        
        for (int ii = 0; ii < NUMS; ++ii) { // create 15 random ints
            randArray[ii] = rand.nextInt(19) + 1; // range 1 to 20
        }
        
        int numEven = 0;
        
        for (int ele : randArray) { // print out array
            System.out.print(ele);
            System.out.print(' ');
        }
        
        System.out.println(' ');
        
        for (int ele : randArray) { // count even
            if (ele % 2 == 0) { // if even | == 1 if we want odd
                ++numEven;
            }   
        }
        
        System.out.print("Numbers even: "); // print out
        System.out.println(numEven);
        System.out.print("Numbers odd: ");
        System.out.println(NUMS - numEven);
    }
}
