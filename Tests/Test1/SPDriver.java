
/**
 * This is a simple driver that tests the Speed Converter class.
 *
 * @author Aryan Gupta
 * @version 1.0
 */
public class SPDriver
{
    
    /**
     * Program entry point
     *
     * @param args The command line parameters
     */
    public static void main(String[] args) {
        SpeedConverter sc = new SpeedConverter();
        java.util.Scanner in = new java.util.Scanner(System.in);
        while (true) { // loop until user wants to quit
            double value = 0;
            boolean exit = false;
            while (true) { // loop until user gives a valid value
                System.out.println("Please enter the value you would like to convert in miles per hour, and 'Q' to quit");
                System.out.print("Value:: ");
                
                try {
                    value = in.nextDouble();
                } catch (Exception e) { // Bad practice but forgot to print out the exceptions that nextDouble() throws
                    String tmp = in.next(); // our incorrect value is still in the buffer so we want to eat it
                    if (tmp.toUpperCase().charAt(0) == 'Q') { // quit is user presses a 'q' or 'Q'
                        exit = true;
                        break;
                    }
                    System.out.println("Please try again...");
                    continue;
                }
                
                if (value < 0) { // negative value is not a valid value
                   System.out.println("Please enter a valid Value");
                   continue;
                }
                break;
            }
            
            if (exit) break;
            
            sc.setMilesPerHour(value);
            
            System.out.println(sc); // pretty print our converter
        }
        
        System.out.println("GoodBye");
    }
}
