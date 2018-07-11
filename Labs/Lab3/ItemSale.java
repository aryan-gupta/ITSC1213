/**
* This class holds data for a sales slip after a discount is applied.
*
* @author (type your name)
* @version (type a version number or a date)
*/
public class ItemSale
{
	private double costOfOneItem;
	private int numberOfItems;
	private double discountRate;
	/** This is the no-args constructor that sets up initial
	* values for the fields. All numeric fields are set to 0.
	*/
	public ItemSale( )
	{
		costOfOneItem = 0;
		numberOfItems = 0;
		discountRate = .05;
	}
	/** This is the method allows the user to set a value for the cost
	* of an item.
	* @param double- the value to change the cost field to
	*/
	public void setCost(double inCost)
	{
		if(inCost < 0)
		costOfOneItem = 0;
		else
		costOfOneItem = inCost;
	}

	/** This is the method allows the user to set a value for the number
	* of items purchased.
	* @param int- the value to change the numberOfItems field
	*/

	// Do not put this box of text in your code
	// ______________________________________________________
	// The following method introduces the ternary operator.
	// It is a short way of writing an if-else statement.
	// The ternary operator works like this:
	// Relational or logical expression ? what to do if expression is true:
	// what to do if expression is false
	// The code in the following method is the same as:
	// if(inNumber < 0)
	// numberOfItems = 0;
	// else
	// numberOfItems = inNumber;
	public void setNumberOfItems(int inNumber)
	{
		numberOfItems = inNumber < 0? 0:inNumber;
	}



	/** This is the method allows the user to set a value for the
	* discount rate.
	*
	* @param double- a number that represents the percent discount
	*/
	public void setDiscount(double inDiscountRate)
	{
		discountRate = inDiscountRate < 0? 0: inDiscountRate;
	}

	/**
	* This method returns the price of the sales based on
	* the price per item, the number of items and the
	* amount of the discount.
	* @return A double value representing the total amount due
	*/

	public double getFinalCost( )
	{
		double finalCost;//finalCost is a local variable to this method
		finalCost = numberOfItems * (costOfOneItem * (1 - discountRate));

		return finalCost;
	}
}//end of the class definition