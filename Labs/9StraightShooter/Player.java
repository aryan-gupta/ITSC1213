/**
 * A Player in the game of Straight Shooter
 *
 * @author Aryan Gupta
 * @version 1.0
 */

public class Player {
	private final int mNum;
	private final Die[] mDie;
	private int mPoints;
	
	/**
	 * Is private to prevent a player without null ID
	 */
	private Player() {
		mNum = 0;
		mDie = null;
	}
	
	/**
	 * Creates a player with ID \p num
	 *
	 * @param int num - the ID of the player
	 */
	public Player(int num) {
		mNum = num;
		mDie = new Die[6];
		for (int ii = 0; ii < mDie.length; ++ii)
			mDie[ii] = new Die();
		mPoints = 0;
	}
	
	
	/**
	 * Does a turn of a player
	 */
	public void doTurn() {
		System.out.println("Player " + mNum + " rolling: ");
		
		int[] rolls = new int[mDie.length];
		for (int ii = 0; ii < mDie.length; ++ii) {
			rolls[ii] = mDie[ii].getNewRoll();
			System.out.println(mDie[ii]);
		}
		
		int oneCounter = 0;
		for (int roll : rolls) {
			if (roll == 1) ++oneCounter;
			if (oneCounter == 3) {
				mPoints = 0;
				return;
			}				
		}
		
		java.util.Arrays.sort(rolls);
		
		int num = 0;
		for (int roll : rolls) {
			if (roll != (num + 1)) {
				addPoints(num);
				break;
			}
			++num;
		}
		
		System.out.println("Current Points: " + mPoints);
	}
	
	/**
	 * Returns if this player has won or not
	 *
	 * @return boolean - if this player has won
	 */
	public boolean getWin() {
		return mPoints >= 100;
	}

	/**
	 * Adds the respective amounts of the highest straight shot we got
	 *
	 * @param int roll - the highest roll we got
	 */
	private void addPoints(int roll) {
		int originalPoints = mPoints;
		switch(roll) {
			case 0: mPoints +=  0; break;
			case 1: mPoints +=  5; break;
			case 2: mPoints += 10; break;
			case 3: mPoints += 15; break;
			case 4: mPoints += 20; break;
			case 5: mPoints += 25; break;
			case 6: mPoints += 35; break;
		}
		System.out.println("Points from roll: " + (mPoints - originalPoints));
	}
}