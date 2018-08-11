
/**
 * Represets a Pet in an pet adoption service. 
 * Stores the name, type and the cost to adopt 
 *
 * @author Aryan Gupta
 * @version 1.0
 */
public class Pet
{
    private String mType; /// The type of pet
    private String mName; /// The name of the pet
    private double mCostToAdopt; /// The cost to adopt this pet
    
    /**
     * Pet Constructor - Creates a pet with default values, values are unknown and 0.0
     *
     */
    public Pet() {
        this("unknown", "unknown", 0.00);
    }
    
    /**
     * Pet Constructor - Creates a pet with the parameters passed in to the constructor
     *
     * @param String type - The type of pet to create
     * @param String name - The name of the pet
     * @param double cta - The cost to adopt this pet
     */
    public Pet(String type, String name, double cta) {
        mType = type;
        mName = name;
        mCostToAdopt = cta;
    }
    
    /**
     * Method toString - Returns the String representation of this object
     *
     * @return String - The String representation of this object
     */
    public String toString() {
        return "A " + mType + " named " + mName + " costs " + mCostToAdopt;
    }
    
    /**
     * Method equals - Tests if 2 Pets are the same. Uses the type of pet
     * and the name of the pet
     *
     * @param Pet o - The Pet to compare this to 
     * @return boolean - If the 2 pets are the same type and have the same name
     */
    public boolean equals(Pet o) {
        return mType.equals(o.mType) && mName.equals(o.mName);
    }
    
    /**
     * Method compareTo - Compares 2 Pets to see which one costs more
     *
     * @param Pet o - The Pet to compare this to
     * @return int - >0 if this pet costs more
     *               <0 if other pet costs more
     *               =0 if they cost the same amount
     */
    public int compareTo(Pet o) {
        return Double.compare(mCostToAdopt, o.mCostToAdopt);
    }
}
