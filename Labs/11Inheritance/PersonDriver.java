
/** 
 * This class tests the classes: Person, Customer, and PrefferecCustomer
 * 
 * @author Aryan Gupta
 * @version 1.0
 */
 
public class PersonDriver {
    public static void main(String[] str) {
        Person[] people = new Person[4];
        
        people[0] = new            Person("Jerome E Ortiz",     "660 Anthony Avenue",  "325-784-1757");
        people[1] = new          Customer("Eunice G You",       "88 Oxford Court",     "662-265-3504", 598201012, true);
        people[2] = new PreferredCustomer("Robert H Ruvalcaba", "2827 Poco Mas Drive", "214-475-9415", 831776363, false, 3000);
        people[3] = new PreferredCustomer("Robert S Brown",     "886 Ryan Road",       "605-524-3830", 380306399, false,  500);
        
        for (Person p : people) {
            System.out.println(p);
        }
        
        System.out.println(people[1].getName());
        System.out.println(people[2].getAddress());
        System.out.println(people[3].getPhone());
        
        // In order to access these, we need to cast it
        System.out.println(((Customer)people[2]).getID());
        System.out.println(((Customer)people[3]).isInMailingList());
    }
}