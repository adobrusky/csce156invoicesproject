/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: prints the summary report and the invoice details report based on the parsed lists from the .dat files
 */
package com.bc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InvoiceReport {

	public static void main(String[] args) throws IOException {
		
		//Output the summary report and invoice details report to the output.txt file
		Report.write(ParseInvoices.getInvoices(), "data/output.txt");
		BufferedReader in = new BufferedReader(new FileReader("data/output.txt"));
		String line = in.readLine();
		while(line != null) {
			System.out.println(line);
			line = in.readLine();
		}
		in.close();
	}

}
