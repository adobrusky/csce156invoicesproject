package com.bc;

import java.util.List;

public class SummaryReport {
	
	//Print the summary report
	public static String print(List<Invoice> invoices) {
		
		String columnFormat = "%-10s%-20s%-30s%-15s%-15s%-15s%-15s%-15s\n";
		
		//Top layer formatting
		String result = "";
		result += "Executive Summary Report:\n\n";
		result += String.format(columnFormat, "Code", "Owner", "Customer Account", "Subtotal", "Discounts", "Fees", "Taxes", "Total");
		result += DataConverter.multiplyString("-", 132) + "\n";
		
		//Body of report
		for(Invoice i : invoices) {
			result += String.format(columnFormat, i.getInvoiceCode(), i.getOwner().getLastName() + "," + i.getOwner().getFirstName(), 
					i.getCustomer().getName(), "$", "$", "$", "$", "$");
		}
		
		//Bottom layer formatting
		result += DataConverter.multiplyString("=", 132) + "\n";
		result += String.format(columnFormat, "TOTALS", "", "", "", "", "", "", "");
		return result;
	}
}
