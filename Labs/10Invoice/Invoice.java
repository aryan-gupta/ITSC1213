

public class Invoice {
	String invoiceID;
	String description;
	double amount;
	boolean isPaid;
	
	public Invoice() {
		this.invoiceID = new String("");
		this.description = new String("");
		this.amount = 0.0;
		this.isPaid = false;
	}
	
	public Invoice(String id, String desc, double amount, boolean paid) {
		this.invoiceID = new String(id);
		this.description = new String(desc);
		this.amount = amount;
		this.isPaid = paid;
	}
	
	public Invoice(String data, String delim) {
		java.util.StringTokenizer st = new java.util.StringTokenizer(data, delim);
		this.invoiceID = new String(st.nextToken());
		this.description = new String(st.nextToken());
		this.amount = Double.parseDouble(st.nextToken());
		this.isPaid = Boolean.parseBoolean(st.nextToken());
	}
	
	public String toString() {
		return 
		"Invoice #" + invoiceID + ": (" + description + ") of $" + amount + " is" + ((isPaid)? " ": " not ") + "paid.";
	}
	
	public int compareTo(Invoice o) {
		return Double.compare(amount, o.amount);
	}
	
	public boolean equals(Invoice o) {
		return invoiceID.equals(o.invoiceID);
	}
	
	public void setPaid(boolean paid) {
		isPaid = paid;
	}
	
	public void setAmount(double amnt) {
		amount = amnt;
	}
}