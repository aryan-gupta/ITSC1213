 
/** 
 * This class represents a person
 * 
 * @author Aryan Gupta
 * @version 1.0
 */
public class Person {
    private String mName;
    private String mAddress;
    private String mPhone;
    
    public Person() {
        this("unknown");
    }
    
    public Person(String name) {
        this(name, "unknown", "unknown");
    }
    
    public Person(String name, String address, String phone) {
        mName = new String(name);
        setAddress(address);
        setPhone(phone);
    }
    
    public String getName() {
        return mName;
    }
    
    public String getAddress() {
        return mAddress;
    }
    
    public String getPhone() {
        return mPhone;
    }
    
    public void setName(String name) {
        mName = new String(name);
    }
    
    public void setAddress(String address) {
        mAddress = new String(address);
    }
    
    public void setPhone(String phone) {
        mPhone = new String(phone);
    }
    
    public String toString() {
        return mName + " lives at " + mAddress + " and can be contacted at " + mPhone;
    }
}