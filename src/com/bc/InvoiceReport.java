/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: prints the summary report and the invoice details report based on the parsed lists from the .dat files
 */
package com.bc;

import java.io.IOException;

public class InvoiceReport {

	public static void main(String[] args) throws IOException {
		
		//Output the summary report and invoice details report to the output.txt file
		//System.out.println(SummaryReport.print(ParseInvoices.getInvoices()) + "\n\n" + DetailedReport.print(ParseInvoices.getInvoices()));
		for(Product i : ParseProducts.getProducts()) {
			System.out.println(i);
		}
	}
	
}
