package com.bc;

public class TestADT {

	public static void main(String[] args) {
		InvoiceList<Invoice> invoices = ParseInvoices.getInvoices();
		System.out.println(invoices.toString());
		
	}

}
