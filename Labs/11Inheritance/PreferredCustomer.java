
/** 
 * This class represents a Preffered Customer
 * 
 * @author Aryan Gupta
 * @version 1.0
 */
public class PreferredCustomer extends Customer {
    private int mPurchases;
    private double mDiscount;
    
    public PreferredCustomer() {
        this(0);
    }
    
    public PreferredCustomer(int purchases) {
        setPurchases(purchases);
    }
    
    public PreferredCustomer(String name, String address, String phone, int id, boolean sub, int purchases) {
        super(name, address, phone, id, sub);
        setPurchases(purchases);
    }
    
    public int getPurchases() {
        return mPurchases;
    }
    
    public double getDiscount() {
        return mDiscount;
    }
    
    public void setPurchases(int purchases) {
        mPurchases = purchases;
        calculateDiscount();
    }
    
    private void calculateDiscount() {
        if        (mPurchases > 2000) {
            mDiscount = 0.1;
        } else if (mPurchases > 1500) {
            mDiscount = 0.07;
        } else if (mPurchases > 1000) {
            mDiscount = 0.06;
        } else if (mPurchases >  500) {
            mDiscount = 0.05;
        } else {
            mDiscount = 0.0;
        }
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder(super.toString());
        str.append(" and has spent " + mPurchases + " and gets " + (mDiscount * 100) + "% discount on all items");
        return str.toString();
    }
}