package com.bc;

public class TestADT {

	public static void main(String[] args) {
		InvoiceList<Invoice> invoices = ParseInvoices.getInvoices();
		
		for(Invoice i : invoices) {
			System.out.println(i.getInvoiceCode());
		}
		int test = 5 / 2;
		System.out.println(test);
		
	}

}
