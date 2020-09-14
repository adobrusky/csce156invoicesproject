package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataConverter {
	
	public static Person findPrimaryContact(String code, List<Person> people) {
		//Finds a primary contact based on the given code
		for(Person i : people) {
			if(i.getCode().equals(code)) {
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
	
	public static List<Customer> parseCustomers(List<Person> people) {
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
    		Person primaryContact = findPrimaryContact(tokens[3], people);
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
	
	public static void main(String[] args) {
		
		//Creates a list of people from the Persons.dat
		List<Person> persons = parsePersons();
		
		//Creates a list of customers from the Customers.dat
		List<Customer> customers = parseCustomers(persons);
		
		//Creates a list of products from the Products.dat
		List<Product> products = parseProducts();
    	
		//Converts the objects to json and outputs the parsed data into json files
		Json_write.printJSON("data/persons1.json", persons, "persons");
		Json_write.printJSON("data/customers1.json", customers, "customers");
		Json_write.printJSON("data/products1.json", products, "products");
		
		//Converts the objects to xml and outputs the parsed data into xml files
		Xml_write.printXML("data/persons1.xml", persons, "persons");
		Xml_write.printXML("data/customers1.xml", customers, "customers");
		Xml_write.printXML("data/products1.xml", products, "products");
	}

}
