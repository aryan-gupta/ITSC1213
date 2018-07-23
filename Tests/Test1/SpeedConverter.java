
/**
 * This class converts a mile per hour value to miles per minute, feet per hour, and 
 * feet per minute value. 
 *
 * @author Aryan Gupta
 * @version 1.0
 */
public class SpeedConverter
{
    private double milesPerHour; // The value in miles per hour
    private double milesPerMinute; // The value in miles per minute
    private double feetPerHour; // The value in feet per hour
    private double feetPerMinute; // The value in feet per minute
    
    private final int FEET_PER_MILE; // conversion factor for feet to miles
    private final int MINUTES_PER_HOUR; // conversion factor for minutes to hours
    
    /**
     * SpeedConverter Constructor. Default constructs out class with all 
     * values equal to 0's
     *
     */
    public SpeedConverter() {
        this(0.0, 0.0, 0.0, 0.0); 
    }
    
    /**
     * SpeedConverter Constructor. Constructs this class with the miles per hour
     * value that we want to convert
     *
     * @param mph A value in miles per hour that we want to convert
     */
    public SpeedConverter(double mph) {
        this(mph, 0.0, 0.0, 0.0);
        update();
    }
    
    /**
     * SpeedConverter Constructor. A private constructor primarly used to reduce code redundancy.
     * Because of this constructor we can change our constants at one place and it will reflect
     * everywhere. Takes in parameters for each member variable. Because of this, this c'tor is
     * private to prevent the e____ (the correct word is escaping me at the moment) from breaking.
     * (The e___ word means the truths that the programmer can see from outside the class)
     *
     * @param mph The value to set milesPerHour to
     * @param mpm The value to set milesPerMinute to
     * @param fph The value to set feetPerHour to
     * @param fpm The value to set feetPerMinute to
     */
    private SpeedConverter(double mph, double mpm, double fph, double fpm) {
        FEET_PER_MILE = 5280;
        MINUTES_PER_HOUR = 60;
        
        milesPerHour = mph;
        milesPerMinute = mpm;
        feetPerHour = fph;
        feetPerMinute = fpm;
    }
    
    /**
     * Method setMilesPerHour. Sets the milesPerHour value and updates the e____.
     *
     * @param mph The value to set milesPerHour to
     */
    public void setMilesPerHour(double mph) {
        milesPerHour = mph;
        update();
    }
    
    /**
     * Method getMilesPerHour. Returns the value in miles per hour
     *
     * @return The value in miles per hour
     */
    public double getMilesPerHour() {
        return milesPerHour;
    }
    
    /**
     * Method getMilesPerMinute. Returns the value in miles per minute
     *
     * @return The value in miles per minute
     */
    public double getMilesPerMinute() {
        return milesPerMinute;
    }
    
    /**
     * Method getFeetPerHour. Returns the value in feet per hour
     *
     * @return The value in feet per hour
     */
    public double getFeetPerHour() {
        return feetPerHour;
    }
    
    /**
     * Method getFeetPerMinute. Returns the value in feet per minute
     *
     * @return The value in feet per minute
     */
    public double getFeetPerMinute() {
        return feetPerMinute;
    }
    
    /**
     * Updates the internal e____ after the milesPerHour value has changed
     *
     */
    private void update() {
        feetPerHour    = milesPerHour * FEET_PER_MILE;
        feetPerMinute  = feetPerHour  / MINUTES_PER_HOUR;
        milesPerMinute = milesPerHour / MINUTES_PER_HOUR;
    }
    
    /**
     * Method toString. Converts this class to a String representation
     *
     * @return The String representation of thie object
     */
    public String toString() {
        return milesPerHour + " mph is equal to " + milesPerMinute + " miles per minute, " + feetPerHour + " feet per hour, or " + feetPerMinute + " feet per minute";
    }
}
