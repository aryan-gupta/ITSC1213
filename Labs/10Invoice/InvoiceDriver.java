

public class InvoiceDriver {
	public static void main(String[] str) throws java.io.FileNotFoundException {
		int numInvoices = 10;
		
		Invoice[] invoices = new Invoice[numInvoices];
		
		java.util.Scanner file = new java.util.Scanner(new java.io.File("invoicedata.txt"));
		
		int idx = 0;
		while (file.hasNext()) {
			invoices[idx++] = new Invoice(file.nextLine(), ",");
		}
		
		for (Invoice i : invoices) {
			System.out.println(i);
		}
		
		if (invoices[0].compareTo(invoices[numInvoices - 1]) > 0)
			System.out.println("First Invoice is bigger");
		else
			System.out.println("Last Invoice is bigger");
		
		if (invoices[1].equals(invoices[4]))
			System.out.println("They are equal");
		else
			System.out.println("They are not equal");
	}
}