package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PortfolioReport {
	
	public static List<Invoice> parseInvoices() {
		//Scans info from Invoices.dat and parses it into objects of invoice and returns a list of invoices
		Scanner s = null;
    	try {
			s = new Scanner(new File("data/Invoices.dat"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
    	
    	int invoiceSize = Integer.parseInt(s.nextLine());
    	List<Invoice> invoices = new ArrayList<Invoice>(invoiceSize);
    	
    	while(s.hasNext()) {
    		String line = s.nextLine();
    		String tokens[] = line.split(";");
    		String code = tokens[0];
    		String fullName[] = tokens[1].split(",");
    		String lastName = fullName[0];
    		String firstName = fullName[1];
    		String fullAddress[] = tokens[2].split(",");
    		String street = fullAddress[0];
    		String city = fullAddress[1];
    		String state = fullAddress[2];
    		String zip = fullAddress[3];
    		String country = fullAddress[4];
    		String emails[] = {""};
    		
    		if(tokens.length > 3) {
        		emails = tokens[3].split(",");
    		}
    		
    		//invoices.add(new Person(code, lastName, firstName, new Address(street, city, state, zip, country), emails));	
    	}
    	
    	s.close();
    	return invoices;
	}

	public static void main(String[] args) {
		
		//Creates a list of invoices from the Invoices.dat
		//I just copied in the code from parsePersons and i have only changed variable names so it doesnt work
		List<Invoice> invoices = parseInvoices();
		
	}

}
