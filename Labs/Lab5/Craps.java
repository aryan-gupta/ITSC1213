
/**
 * Implements the 2 dice scnario of the game Craps
 *
 * @author Aryan Gupta
 * @version 1.0
 */
public class Craps
{
    private Die mDie1; // die number 1
    private Die mDie2; // die number 2
    
    
    /**
     * Craps Constructor. Creates 2 dice
     *
     */
    public Craps() {
        mDie1 = new Die();
        mDie2 = new Die();
    }
    
    /**
     * Method getSum. Rolls the 2 dice and returns the sum
     *
     * @return int - the sum of the 2 dice
     */
    public int getSum() {
        return mDie1.getNewRoll() + mDie2.getNewRoll();
    }
}
