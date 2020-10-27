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
		InvoiceData.addPerson("123456", "John", "Doe", "45 Wall St", "New York", "XX", "5436", "India");
	}

}