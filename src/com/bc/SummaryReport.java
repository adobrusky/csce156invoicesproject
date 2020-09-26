package com.bc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//SummaryReport prints the summary report
public class SummaryReport extends InvoiceReport {
	
	public static String print(List<Invoice> invoices) {
		
		String columnFormat = "%-10s%-30s%-30s%-13s%-13s%-13s%-13s%-13s\n";
		
		//Top layer formatting
		String result = "";
		result += "Executive Summary Report:\n\n";
		result += String.format(columnFormat, "Code", "Owner", "Customer Account", " Subtotal", " Discounts", " Fees", " Taxes", " Total");
		result += multiplyString("-", 135) + "\n";
		
		//Body of report TODO:replace placeholder 20.25 with actual numbers
		List<Invoice> sortedTemp = new ArrayList<Invoice>(invoices);
		Collections.sort(sortedTemp);
		
		for(Invoice i : sortedTemp) {
			result += String.format(columnFormat, i.getInvoiceCode(), i.getOwner().getLastName() + ", " + i.getOwner().getFirstName(), 
					i.getCustomer().getName(), $(20.25), $(20.25), $(20.25), $(20.25), $(20.25));
		}
		
		//Bottom layer formatting
		result += multiplyString("=", 135) + "\n";
		result += String.format("%-70s%-13s%-13s%-13s%-13s%-13s\n", "TOTALS", $(32), $(32), $(32), $(32), $(32));
		return result;
	}
}
