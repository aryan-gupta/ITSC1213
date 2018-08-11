
/** 
 * This class represents a customer
 * 
 * @author Aryan Gupta
 * @version 1.0
 */
public class Customer extends Person {
    private int mID;
    private boolean mInMailingList;
    
    public Customer() {
        this(0, false);
    }
    
    public Customer(int id) {
        this(id, false);
    }
    
    public Customer(boolean sub) {
        this(0, sub);
    }
    
    public Customer(int id, boolean sub) {
        setID(id);
        subscribe(sub);
    }
    
    public Customer(String name, String address, String phone, int id, boolean sub) {
        super(name, address, phone);
        setID(id);
        subscribe(sub);
    }
    
    public int getID() {
        return mID;
    }
    
    public boolean isInMailingList() {
        return mInMailingList;
    }
    
    public void setID(int id) {
        mID = id;
    }

    public void subscribe(boolean sub) {
        mInMailingList = sub;
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder(super.toString());
        str.append(" has ID: " + mID + " and is" + ((mInMailingList)? " ": " not ") + "in the mailing list");
        return str.toString();
    }
}