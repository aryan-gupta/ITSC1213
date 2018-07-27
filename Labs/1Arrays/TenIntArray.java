
/**
 * Write a program that declares and creates two arrays. Each array will hold ten integers. Read in
 * values for the first array from the user. Then assign the values into the second array in reverse
 * order. Print the contents of both arrays to verify the first array was reversed in the second
 * array
 *
 * @author Aryan Gupta
 * @version 1.0
 */

import java.util.Scanner;

public class TenIntArray
{    
    public static void FillPrintArray() {
        Scanner in = new Scanner(System.in);

        int[] array1 = new int[10];
        int[] array2 = new int[10];
        
        for (int ii = 0; ii < 10; ++ii) { // get inputs from user
            System.out.print("Type Digit:: ");
            array1[ii] = in.nextInt();
        }
        
        for (int ii = 0; ii < 10; ++ii) { // reverse the array into the 2nd array
            array2[ii] = array1[9 - ii];
        }
        
        System.out.print("Array 1: ");
        for (int ii = 0; ii < 10; ++ii) { // print array1
            System.out.print(array1[ii]);
            System.out.print(' ');
        }

        System.out.print("\nArray 2: ");
        for (int ii = 0; ii < 10; ++ii) { // print array2
            System.out.print(array2[ii]);
            System.out.print(' ');
        }
    }
}
