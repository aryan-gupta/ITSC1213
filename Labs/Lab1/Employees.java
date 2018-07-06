
/**
 * Write a description of class Employees here.
 * A simple database of employees where IDs map to their sale
 * numbers, we do basic data analysis such as largest sale
 * and average sales. would be cool to use a hash table or similar
 *
 * @author Aryan Gupta
 * @version 1.0
 */

import java.util.Scanner;

public class Employees
{
    private static final int NUMEM = 10;
    public static void createEmployees() {
        Scanner in = new Scanner(System.in);
        
        int[] employeeID = new int[NUMEM];
        int[] saleAmount = new int[NUMEM];
        
        for (int ii = 0; ii < NUMEM; ++ii) { // input all the ids and sales
            System.out.print("Type ID:: ");
            employeeID[ii] = in.nextInt();
            
            System.out.print("Type Sale Amount:: ");
            saleAmount[ii] = in.nextInt();
        }
        
        System.out.println("ID        Sales");
        System.out.println("--        -----");
        
        for (int ii = 0; ii < NUMEM; ++ii) { // pretty print them out
            System.out.print(employeeID[ii]);
            System.out.print("          ");
            System.out.println(saleAmount[ii]);
        }
        
        int idx = 0;
        for (int ii = 1; ii < NUMEM ;++ii) { // get the largest one
            if (saleAmount[ii] > saleAmount[idx]) {
                idx = ii;
            }
        }
        
        System.out.println(' ');
        
        System.out.print("ID with highest: "); // print out the largest one
        System.out.println(employeeID[idx]);
        System.out.print("Sales: ");
        System.out.println(saleAmount[idx]);
        
        idx = 0;
        for (int ii = 1; ii < NUMEM ;++ii) { // get the smallest one
            if (saleAmount[ii] < saleAmount[idx]) {
                idx = ii;
            }
        }
        
        System.out.println(' ');
        
        System.out.print("ID with lowest: "); // print
        System.out.println(employeeID[idx]);
        System.out.print("Sales: ");
        System.out.println(saleAmount[idx]);
        
        long sum = 0;
        for (int ele : saleAmount) { // add up all the sales for the averging
            sum += ele;
        }
        
        float avg = (float)sum / NUMEM; // average, double precision is not needed
        
        System.out.print("Average Sales: ");
        System.out.println(avg);
        
        for (int ii = 0; ii < NUMEM; ++ii) {
            if (saleAmount[ii] < avg) { // get all employees with lower than average sales and print them
                System.out.print(employeeID[ii]);
                System.out.print(" has sales below average: ");
                System.out.println(saleAmount[ii]);
            }
        }
        
        for (int ii = 0; ii < NUMEM; ++ii) {
            if (saleAmount[ii] > avg) { // print emplyees will below average sales
                System.out.print(employeeID[ii]);
                System.out.print(" has sales above average: ");
                System.out.println(saleAmount[ii]);
            }
        }
        
        System.out.print("Insert an ID: ");
        int ID = in.nextInt();
        idx = -1; // ints are signed
        for (int ii = 0; ii < NUMEM; ++ii) {
            if (employeeID[ii] == ID) { // if we find the ID
                idx = ii;
            }
        }
        
        if (idx == -1) { // if we dont find the ID the loop runs empty and we get the initial value of idx
            System.out.print("ID not found");
        } else { // found
            System.out.print("Found Employee with ID: ");
            System.out.println(ID);
            System.out.print("Sales for employee: ");
            System.out.println(saleAmount[idx]);
        }
    }
}
