/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: prints the summary report and the invoice details report based on the data from the database
 */
package com.bc;

import java.io.IOException;

public class InvoiceReport {

	public static void main(String[] args) throws IOException {
		
		//Output the summary report and invoice details report to the standard output
		System.out.println(SummaryReport.print(ParseInvoices.getInvoices()) + "\n\n" + DetailedReport.print(ParseInvoices.getInvoices()));
	}
	
}
