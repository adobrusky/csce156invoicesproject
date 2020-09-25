package com.bc;

import java.util.Arrays;
import java.util.List;

//InvoiceDetails prints the invoice details report
public class InvoiceDetails extends InvoiceReport {

	public static String print(List<Invoice> invoices) {
		
		//String columnFormat = "%-10s%-20s%-30s%-12s%-12s%-12s%-12s%-12s\n";
		
		//Top layer formatting
		String result = "";
		result += "Invoice Details:\n";
		result += multiplyString("=+", 55) + "\n";
		
		//Body formatting
		for(Invoice i : invoices) {
			result += "Invoice " + i.getInvoiceCode() + "\n";
			result += multiplyString("-", 50) + "\n";
			result += "Owner:\n" + String.format("\t%s,%s\n\t%s\n\t%s\n\t%s, %s %s %s\n", i.getOwner().getLastName(), i.getOwner().getFirstName(), 
					Arrays.toString(i.getOwner().getEmails()), i.getOwner().getAddress().getStreet(), i.getOwner().getAddress().getCity(), 
							i.getOwner().getAddress().getState(), i.getOwner().getAddress().getCountry(), i.getOwner().getAddress().getZip());
		}
		
		return result;
	}
}
