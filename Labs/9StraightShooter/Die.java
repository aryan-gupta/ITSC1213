
/**
 * An implementation of a multi-sided dice
 *
 * @author Aryan Gupta
 * @version 1.0
 */

import java.util.Random;

public class Die
{
    private int mNumSides;
    private int mSideUp;
	private Random mRand;
    
    
    /**
     * Die Constructor. Creates a 6 sided die
     *
     */
    public Die() {
        mRand = new Random();
        mNumSides = 6; // default initializes as a cube dice
        roll();
    }
    
    /**
     * Die Constructor. Creates a die with @p sides sides
     *
     * @param sides The number of sides the dice should have
     */
    public Die(int sides) {
		mRand = new Random(System.currentTimeMillis());
        mNumSides = sides;
        roll();
    }
    
    /**
     * Method getDieSides. Returns the number of sides on the die
     *
     * @return int - The number of sides
     */
    public int getDieSides() {
        return mNumSides;
    }
    
    /**
     * Method getRoll. Returns the last roll
     *
     * @return int - The face up value of the last roll
     */
    public int getRoll() {
        return mSideUp;
    }
    
    /**
     * Method getNewRoll. Rolls the dice and returns the new faceup value
     *
     * @return The face up value of the new roll
     */
    public int getNewRoll() {
        roll();
        return getRoll();
    }
    
    
    
    /**
     * Method setDieSides. Changes the number of sides on the dice
     *
     * @param sides The new number of sides the die should have
     */
    public void setDieSides(int sides) {
        mNumSides = sides;
    }
    
    // isnt the purpose of data hiding to prevent this?
    // why does this function exist?
    /**
     * Method setRoll. isnt the purpose of data hiding to prevent this?
     * why does this function exist? But cheats and sets the roll to what
     * ever the user passes in
     *
     * @param num The new face up value of the die
     */
    public void setRoll(int num) {
        if (num < 1 || num > mNumSides)
            return; // if we throw then we need to handle it
        mSideUp = num;
    }
    
    /**
     * Method roll. Rolls the die with a new value
     *
     */
    public void roll() {
        // https://docs.oracle.com/javase/8/docs/api/java/util/Random.html#nextInt-int-
        // bound is exclusive so this works in our favor
        mSideUp = mRand.nextInt(mNumSides) + 1;
    }
    
    /**
     * Method toString. Converts this class to a string
     *
     * @return The String representation of this class
     */
    public String toString() {
        return "---------\n" + "|    " + mSideUp + "    |\n" + "---------\n";
    }
}
