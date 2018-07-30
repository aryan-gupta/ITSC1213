

import java.util.StringTokenizer;

public class Book implements Comparable<Book> {
	public final String mTitle;
	public final String mAuthor;
	public final String mPublisher;
	public final String mISBN;
	public final String mPubDate;
	
	public double mPrice;
	public int mAvailable;
	
	private Book() {
		mTitle     = null;
		mAuthor    = null;
		mPublisher = null;
		mISBN      = null;
		mPubDate   = null;
	}
	
	public Book(
		String title,
		String author,
		String pub,
		String isbn,
		String pubDate,
		double price,
		int avail
	) {
		mTitle = new String(title); // deep copy? copy internal data
		mAuthor = new String(author);
		mPublisher = new String(pub);
		mISBN = new String(isbn);
		mPubDate = new String(pubDate);
		mPrice = price;
		mAvailable = avail;
	}
	
	public Book(String data, String delim) {
		StringTokenizer st = new StringTokenizer(data, delim);
		String title  = st.nextToken();
		String author = st.nextToken();
		String pub    = st.nextToken();
		double price  = Double.parseDouble(st.nextToken());
		String isbn   = st.nextToken();
		String date   = st.nextToken();
		int avail     = Integer.parseInt(st.nextToken());
		
		// this(title, author, pub, isbn, date, price, avail);
		mTitle = new String(title); // deep copy? copy internal data
		mAuthor = new String(author);
		mPublisher = new String(pub);
		mISBN = new String(isbn);
		mPubDate = new String(pub);
		mPrice = price;
		mAvailable = avail;
	}
	
	public void setPrice(double price) {
		mPrice = price;
	}
	
	public void setAvailable(int avail) {
		mAvailable = avail;
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public String getISBN() {
		return mISBN;
	}
	
	public String getAuthor() {
		return mAuthor;
	}
	
	public double getPrice() {
		return mPrice;
	}
	
	public int getAvailable() {
		return mAvailable;
	}
	
	public String toString() {
		return
		mTitle     + "," +
		mAuthor    + "," +
		mPublisher + "," +
		mISBN      + "," +
		mPubDate   + "," +
		mPrice     + "," +
		mAvailable;
	}
	
	public boolean equals(Book o) {
		return mTitle.equals(o.mTitle);
	}
	
	@Override
	public int compareTo(Book o) {
		return mTitle.compareTo(o.mTitle);
	}
}