

public class ConnectX {
	public static void main(String[] args) {
		Board b = new Board(4, 5, 5);
		b.place('X', 2);
		b.place('Y', 2);
		b.place('X', 2);
		b.place('Y', 2);
		b.place('X', 1);
		b.place('Y', 0);
		b.place('X', 0);
		System.out.print(b);
	}
}