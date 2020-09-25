package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//DataConverter class parses all information provided in .dat files into objects and stores them as lists that can be accessed with getter methods
public class DataConverter {
	
	//Stores a list of each type of parsed data
	private static List<Person> persons = parsePersons();
	private static List<Customer> customers = parseCustomers();
	private static List<Product> products = parseProducts();
	private static List<Invoice> invoices = parseInvoices();
	
	//Getter functions for all of the lists
	public static List<Person> getPersons() {
		return persons;
	}

	public static List<Customer> getCustomers() {
		return customers;
	}

	public static List<Product> getProducts() {
		return products;
	}

	public static List<Invoice> getInvoices() {
		return invoices;
	}

	public static Person findPerson(String code) {
		//Finds a primary contact based on the given code
		for(Person i : persons) {
			if(i.getCode().equals(code)) {
				return i;
			}
		}
		return null;
	}
	
	public static Product findProduct(String productCode) {
		//Find the product from the given code
		for(Product i : products) {
			if(i.getCode().equals(productCode)) {
				return i;
			}
		}
		return null;
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
	
	public static List<Person> parsePersons() {
		//Scans info from Persons.dat and parses it into objects of people and returns a list of people
		Scanner s = null;
    	try {
			s = new Scanner(new File("data/Persons.dat"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
    	
    	int peopleSize = Integer.parseInt(s.nextLine());
    	List<Person> people = new ArrayList<Person>(peopleSize);
    	
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
    		
    		people.add(new Person(code, lastName, firstName, new Address(street, city, state, zip, country), emails));	
    	}
    	
    	s.close();
    	return people;
	}
	
	public static List<Customer> parseCustomers() {
		//Scans info from Customers.dat and parses it into objects of customer and returns a list of customers
		Scanner s = null;
    	try {
			s = new Scanner(new File("data/Customers.dat"));
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
    		Person primaryContact = findPerson(tokens[3]);
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

	public static List<Product> parseProducts() {
		//Scans info from Products.dat and parses it into objects of products and returns a list of products
		Scanner s = null;
    	try {
			s = new Scanner(new File("data/Products.dat"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
    	
    	int productSize = Integer.parseInt(s.nextLine());
    	List<Product> products = new ArrayList<Product>(productSize);
    	
    	while(s.hasNext()) {
    		String line = s.nextLine();
    		String tokens[] = line.split(";");
    		String code = tokens[0];
    		char type = tokens[1].charAt(0);
    		String label = tokens[2];
    		switch(type) {
    			case 'R':
    				double dailyCost = Double.parseDouble(tokens[3]);
    				double deposit = Double.parseDouble(tokens[4]);
    				double cleaningFee = Double.parseDouble(tokens[5]);
    				products.add(new Rental(code, type, label, dailyCost, deposit, cleaningFee));
    				break;
    			case 'F':
    				double partsCosts = Double.parseDouble(tokens[3]);
    				double hourlyLaborCosts = Double.parseDouble(tokens[4]);
    				products.add(new Repair(code, type, label, partsCosts, hourlyLaborCosts));
    				break;
    			case 'C':
    				double unitCost = Double.parseDouble(tokens[3]);
    				products.add(new Concession(code, type, label, unitCost));
    				break;
    			case 'T':
    				double costPerMile = Double.parseDouble(tokens[3]);
    				products.add(new Towing(code, type, label, costPerMile));
    				break;
    		}
    	}
    	
    	s.close();
    	return products;
	}
	
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
    		String invoiceCode = tokens[0];
    		String ownerCode = tokens[1];
    		String customerCode = tokens[2];
    		String productToken[] = tokens[3].split(",");
    		List<Product> productList = new ArrayList<Product>();
    		for(int i = 0; i < productToken.length; i++) {
    			String productInfo[] = productToken[i].split(":");
    			Product product = findProduct(productInfo[0]);
    			for(int j = 0; j < productInfo.length; j++) {
    				switch(product.getType()) {
    					case 'R':
    						((Rental)product).setDaysRented(Integer.parseInt(productInfo[1]));
    						break;
    					case 'F':
    						((Repair)product).setHoursWorked(Integer.parseInt(productInfo[1]));
    						break;
    					case 'C':
    						((Concession)product).setQuanity(Integer.parseInt(productInfo[1]));
    						if(productInfo.length == 3) {
        						((Concession)product).setAssociatedRepair(productInfo[2]);
    						}
    						break;
    					case 'T':
    						((Towing)product).setMilesTowed(Integer.parseInt(productInfo[1]));
    						break;
    				}
    				
    			}
    			productList.add(product);
    		}
    		invoices.add(new Invoice(invoiceCode, findPerson(ownerCode), findCustomer(customerCode), productList));
    		
    	}
    	
    	s.close();
    	return invoices;
	}

	public static void main(String[] args) {
		
		//Converts the objects to json and outputs the parsed data into .json files
		jsonWrite.printJSON("data/Persons.json", persons, "persons");
		jsonWrite.printJSON("data/Customers.json", customers, "customers");
		jsonWrite.printJSON("data/Products.json", products, "products");
		
		//Converts the objects to xml and outputs the parsed data into .xml files
		xmlWrite.printXML("data/Persons.xml", persons, "persons");
		xmlWrite.printXML("data/Customers.xml", customers, "customers");
		xmlWrite.printXML("data/Products.xml", products, "products");
	}

}
