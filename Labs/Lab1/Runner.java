
/**
 * Write a description of class Runner here.
 * Runs all three classes, waits for user input between classes
 * and clears the screen
 *
 * @author Aryan Gupta
 * @version 1.0
 */

import java.io.IOException;

public class Runner
{
    public static void main() {
        System.out.print('\u000C');
        TenIntArray.FillPrintArray();
        try {
            System.in.read();
        } catch (IOException e) {}
        System.out.print('\u000C');
        RandomArray.CreateRandomArray();
        try {
            System.in.read();
        } catch (IOException e) {}
        System.out.print('\u000C');
        Employees.createEmployees();
    }
}
