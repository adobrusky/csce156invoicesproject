package com.bc;

import com.bc.ext.InvoiceData;

public class TestJDBC {

	public static void main(String[] args) {

		/**
		 * 2. Method to add a person record to the database with the provided data.
		 * 
		 * @param personCode
		 * @param firstName
		 * @param lastName
		 * @param street
		 * @param city
		 * @param state
		 * @param zip
		 * @param country
		 */
		//InvoiceData.addPerson("123456", "John", "Doe", "45 Wall St", "New York", "XX", "5436", "India");
		//InvoiceData.addRental("fdg", "Test Rental", 10, 30, 40);
		//InvoiceData.addTowing("jkgh", "Test Tow", 8);
		//InvoiceData.addRepair("hhgfh", "Test Rep", 7, 90);
		//InvoiceData.addConcession("iuio", "Test Con", 8);
		//InvoiceData.addInvoice("INV007", "zyx321", "asdf419");
		//InvoiceData.addConcessionToInvoice("INV007", "iuio", 3, "sievub");
		//InvoiceData.addConcessionToInvoice("INV007", "iuio", 10, "");
		//InvoiceData.addTowingToInvoice("INV007", "ugis43", 3);
		InvoiceData.addRentalToInvoice("INV007", "fdg", 5);
		InvoiceData.addRepairToInvoice("INV007", "hhgfh", 11);
	}

}