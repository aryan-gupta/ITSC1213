
import java.util.Arrays;

public class BookDriver {
	public static void main(String[] a) {
		java.util.Scanner file = null;
		try {
			file = new java.util.Scanner(new java.io.File("bookdata.txt"));
		} catch (java.io.FileNotFoundException e) {
			System.out.println("Error 404: File not found");
		}
			
		int numOfBooks = 0;
		if (file.hasNext()) {
			try {
				numOfBooks = Integer.parseInt(file.nextLine());
			} catch (java.lang.Exception e) {
				System.out.println("You done goofed...");
			}
		}
		
		Book[] books = new Book[numOfBooks];
		while (file.hasNext() && --numOfBooks >= 0) {
			books[numOfBooks] = new Book(file.nextLine(), ",");
		}
		
		// print(books);
		
		// System.out.println();
		// System.out.println();
		
		// https://stackoverflow.com/questions/17336174/sorting-class-array-java
		// does java have multiple inheritance? This might get messy. 
		// https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#sort(java.lang.Object[])
		java.util.Arrays.sort(books);
		
		// print(books);
		
		System.out.println("What would you like to do?");
		System.out.println("1) Search for a book");
		System.out.println("2) Get selection of books");
		System.out.println("3) Quit");
		
		System.out.print(":: ");
		
		java.util.Scanner in = new Scanner(System.in);
		int choice = Integer.parseInt(in.nextLine());
		
		switch (choice) {
			case 1: doBinarySearch(books); break;
			case 2: print(books); break;
			case 3: return;
		}
	}
	
	private static void doBinarySearch(Book[] books) {
		System.out.println("Please enter a title:: ");
		java.util.Scanner in = new Scanner(System.in);
		String title = in.nextLine();
		
		int idx = Arrays.binarySearch(books, title);
	}
	
	private static void print(Book[] books) {
		for (Book b : books) {
			System.out.println(b);
		}
	}
}