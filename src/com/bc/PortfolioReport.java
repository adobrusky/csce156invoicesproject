package com.bc;

public class PortfolioReport extends DataConverter {
	
	public static String multiplyString(String in, int rep) {
		//Multiply strings. Makes it easier for formatting the reports with repeated characters
		String result = "";
		for(int i = 0; i < rep; i++) {
			result += in;
		}
		return result;
	}
	
	public static String $(double value) {
		//Formats the inputed value into the currency structure used in the reports
		return String.format("$%10.2f", value);
	}

	public static void main(String[] args) {
		
		//Print the reports
		System.out.println(SummaryReport.print(getInvoices()) + "\n\n");
		System.out.println(InvoiceDetails.print(getInvoices()));
		
	}

}
