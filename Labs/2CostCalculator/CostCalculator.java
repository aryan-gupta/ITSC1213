
/**
 * This is a first program in Java used to illustrate how to use the IDE
 *
 * This class holds data used for calculating the cost of a trip
 *
 * @author Aryan Gupta
 * @version 1.0
 */
public class CostCalculator
{
    private double miles;
    private double gallons;
    private double costPerGallon;
    private double mileage;
    private double costForTrip;
    
    public CostCalculator (double inMiles, double inGallons, double inCostPer) {
        miles = inMiles;
        gallons = inGallons;
        costPerGallon = inCostPer;
        
        mileage = miles / gallons;
        costForTrip = gallons * costPerGallon;
    }
}
