/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: prints the summary report
 */
package com.bc;

public class SummaryReport extends ReportFormat {
	
	public static String print(InvoiceList<Invoice> invoices) {
		
		//Generate max invoiceCode length for formatting of columns
		int maxCode = 0;
		for(Invoice i : invoices) {
			if(i.getInvoiceCode().length() + 1 > maxCode) {
				maxCode = i.getInvoiceCode().length() + 1;
			}
		}
		String columnFormat = "%-" + maxCode + "s%-30s%-30s%-13s%-13s%-13s%-13s%-13s\n";
		
		//Top layer formatting
		String result = "";
		result += "Executive Summary Report:\n\n";
		result += String.format(columnFormat, "Code", "Owner", "Customer Account", " Subtotal", " Discounts", " Fees", " Taxes", " Total");
		result += multiplyString("-", 135) + "\n";
		
		//Body of report
		double grandSubtotal = 0;
		double grandDiscount = 0;
		double grandFees = 0;
		double grandTaxes = 0;
		double grandTotal = 0;
		
		for(Invoice i : invoices) {
			grandSubtotal += i.getSubtotal();
			grandDiscount += i.getDiscount();
			grandFees += i.getFees();
			grandTaxes += i.getTaxes();
			grandTotal += i.getTotal() + i.getLoyaltyDiscount();
			result += String.format(columnFormat, i.getInvoiceCode(), i.getOwner().getLastName() + ", " + i.getOwner().getFirstName(), 
					i.getCustomer().getName(), $(i.getSubtotal()), $(i.getDiscount()), $(i.getFees()), $(i.getTaxes()), $(i.getTotal() + i.getLoyaltyDiscount()));
		
		}
		
		//Bottom layer formatting
		result += multiplyString("=", 135) + "\n";
		result += String.format("%-" + (maxCode + 60) + "s%-13s%-13s%-13s%-13s%-13s\n", "TOTALS", $(grandSubtotal), $(grandDiscount), $(grandFees), $(grandTaxes), $(grandTotal));
		return result;
	}
}
