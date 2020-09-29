package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//ParseCustomers class parses the Customers.dat and stores the parsed list
public class ParseCustomers {
	
	private static List<Customer> customers = parseCustomers();
	
	public static List<Customer> getCustomers() {
		return customers;
	}
	
	public static Customer findCustomer(String customerCode) {
		//Find the customer from the given code
		for(Customer i : customers) {
			if(i.getCode().equals(customerCode)) {
				return i;
			}
		}
		return null;
	}
	
	private static List<Customer> parseCustomers() {
		//Scans info from Customers.dat and parses it into objects of customer and returns a list of customers
		Scanner s = null;
    	try {
			s = new Scanner(new File("data/CustomersE.dat"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
    	
    	int customerSize = Integer.parseInt(s.nextLine());
    	List<Customer> customers = new ArrayList<Customer>(customerSize);
    	
    	while(s.hasNext()) {
    		String line = s.nextLine();
    		String tokens[] = line.split(";");
    		String code = tokens[0];
    		char type = tokens[1].charAt(0);
    		String name = tokens[2];
    		Person primaryContact = ParsePersons.findPerson(tokens[3]);
    		String fullAddress[] = tokens[4].split(",");
    		String street = fullAddress[0];
    		String city = fullAddress[1];
    		String state = fullAddress[2];
    		String zip = fullAddress[3];
    		String country = fullAddress[4];
    		
    		customers.add(new Customer(code, type, name, primaryContact, new Address(street, city, state, zip, country)));
    	}
    	
    	s.close();
    	return customers;
    	
	}
}
