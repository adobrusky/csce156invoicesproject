/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: provides the write function for writing reports to a file
 */
package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Report {
	
	public static void write(List<Invoice> invoices, String filePath) {
		
		try {
			
			PrintWriter out = new PrintWriter(new File(filePath));
			out.write(SummaryReport.print(invoices));
			out.write("\n\n\n");
			out.write(DetailedReport.print(invoices));
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
