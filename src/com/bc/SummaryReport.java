package com.bc;

import java.util.List;

//SummaryReport prints the summary report
public class SummaryReport extends InvoiceReport {
	
	public static String print(List<Invoice> invoices) {
		
		String columnFormat = "%-10s%-20s%-30s%-13s%-13s%-13s%-13s%-13s\n";
		
		//Top layer formatting
		String result = "";
		result += "Executive Summary Report:\n\n";
		result += String.format(columnFormat, "Code", "Owner", "Customer Account", " Subtotal", " Discounts", " Fees", " Taxes", " Total");
		result += multiplyString("-", 130) + "\n";
		
		//Body of report TODO:replace placeholder 20.25 with actual numbers
		for(Invoice i : invoices) {
			result += String.format(columnFormat, i.getInvoiceCode(), i.getOwner().getLastName() + "," + i.getOwner().getFirstName(), 
					i.getCustomer().getName(), $(20.25), $(20.25), $(20.25), $(20.25), $(20.25));
		}
		
		//Bottom layer formatting
		result += multiplyString("=", 130) + "\n";
		result += String.format(columnFormat, "TOTALS", "", "", "", "", "", "", "");
		return result;
	}
}
