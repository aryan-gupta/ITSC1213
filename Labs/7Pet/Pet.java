
/**
 * Write a description of class Pet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pet
{
    private String mType;
    private String mName;
    private double mCostToAdopt;
    
    public Pet() {
        this("unknown", "unknown", 0.00);
    }
    
    public Pet(String type, String name, double cta) {
        mType = type;
        mName = name;
        mCostToAdopt = cta;
    }
    
    public String toString() {
        return "A " + mType + " named " + mName + " costs " + mCostToAdopt;
    }
    
    public boolean equals(Pet o) {
        return mType.equals(o.mType) && mName.equals(o.mName);
    }
    
    public int compareTo(Pet o) {
        return (int)(mCostToAdopt - o.mCostToAdopt);
    }
}
