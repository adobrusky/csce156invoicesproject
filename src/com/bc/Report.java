package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Report {
	
	public static void write(List<Invoice> invoices) {
		
		try {
			
			PrintWriter out = new PrintWriter(new File("data/outputT.txt"));
			out.write(SummaryReport.print(invoices));
			out.write("\n\n\n");
			out.write(DetailedReport.print(invoices));
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
