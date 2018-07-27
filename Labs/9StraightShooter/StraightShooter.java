/**
 *	Plays a game of Straight Shooter
 *
 * @author Aryan Gupta
 * @version 1.0
 */

public class StraightShooter {
	public static void main(String[] args) {		
		Player[] players = new Player[2];
		for (int ii = 0; ii < players.length; ++ii)
			players[ii] = new Player(ii);
		
		java.util.Scanner in = new java.util.Scanner(System.in);
		
		boolean stop = false;
		do {
			for (Player p : players) {
				p.doTurn();
				if (p.getWin()) {
					stop = true;
					break;
				}
				in.nextLine();
				System.out.println("\n\n\n");
			}
		} while (!stop);
	}
}