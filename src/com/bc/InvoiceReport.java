package com.bc;

//InvoiceReport class will print the summary report and the invoice details report based on the parsed lists from the .dat files
public class InvoiceReport {

	public static void main(String[] args) {
		
		//Print the reports
		System.out.println(SummaryReport.print(ParseInvoices.getInvoices()) + "\n\n");
		System.out.println(DetailedReport.print(ParseInvoices.getInvoices()));
		
		//Output the summary report and invoice details report to the output.txt file
//		Report.write(ParseInvoices.getInvoices());
		
	}

}
