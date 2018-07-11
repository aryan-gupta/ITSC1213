import java.util.*;
public class Driver
{
	public static void main(String [ ] args)
	{
		//all of these are local variables to this method
		Scanner input = new Scanner(System.in); //create only ONE
		//Scanner object
		int inNumber;
		double inPriceOfOne;
		double inDiscount;
		double finalCost;
		ItemSale mySales; // a reference variable to an ItemSale

		System.out.println("You will be asked to enter the price of an" +
		" item, \nthe number of items you are purchasing" +
		" and the discount percent. \n\n");
		System.out.print("Please enter the price of the item: ");
		inPriceOfOne = input.nextDouble( );

		System.out.print("How many items are you buying: ");
		inNumber = input.nextInt( );

		System.out.print("What discount percent are you using: ");
		inDiscount = input.nextDouble( );

		mySales = new ItemSale( );
		mySales.setCost(inPriceOfOne);
		mySales.setNumberOfItems(inNumber);
		mySales.setDiscount(inDiscount);

		finalCost = mySales.getFinalCost( );

		System.out.println("Your final cost is: " + finalCost);
	}

}