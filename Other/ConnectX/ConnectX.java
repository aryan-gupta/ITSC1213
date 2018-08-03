

public class ConnectX {
	public static void main(String[] args) {
		Board b = new Board(4, 10, 10);
		//b.place('Y', 2);
		//b.place('X', 2);
		//b.place('Y', 2);
		//b.place('Y', 2);
		//b.place('X', 2);
		//b.place('Y', 2);
		// b.place('Y', 1);
		// b.place('X', 2);
		// b.place('Y', 3);
		// b.place('X', 4);
		// b.place('Y', 1);
		// b.place('X', 2);
		// b.place('Y', 3);
		// b.place('Y', 4);
		// b.place('X', 1);
		// b.place('X', 2);
		// b.place('Y', 3);
		// b.place('Y', 4);
		// b.place('Y', 1);
		// b.place('X', 2);
		// b.place('Y', 3);
		// b.place('Y', 4);
		// b.place('Y', 1);
		// b.place('Y', 2);
		// b.place('X', 3);
		// b.place('X', 4);
		// b.place('Y', 1);
		// b.place('Y', 2);
		// b.place('X', 3);
		// b.place('Y', 4);
		// b.place('X', 1);
		// b.place('X', 2);
		// b.place('X', 3);
		// b.place('X', 4);
		
		// b.place('Y', 1);
		// b.place('Y', 1);
		// b.place('Y', 1);
		// b.place('Y', 2);
		// b.place('Y', 2);
		// b.place('Y', 3);
		// b.place('X', 1);
		// b.place('X', 2);
		// b.place('X', 3);
		// b.place('X', 4);
		
		b.place('Y', 1);
		b.place('Y', 1);
		b.place('Y', 1);
		b.place('Y', 2);
		b.place('Y', 2);
		b.place('Y', 3);
		b.place('X', 1);
		b.place('X', 2);
		b.place('X', 3);
		b.place('X', 4);

		System.out.println(b.getWinner());
		System.out.print(b);
	}
}