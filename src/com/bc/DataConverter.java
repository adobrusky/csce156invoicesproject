package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataConverter {
	
	public static List<Person> parsePeople() {
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
    		String fullName[] = tokens[1].split(", ");
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
	
//	public static List<Customer> parseCustomers() {
//		//Scans info from Customers.dat and parses it into objects of customer and returns a list of customers
//		Scanner s = null;
//    	try {
//			s = new Scanner(new File("data/Customers.dat"));
//		} catch (FileNotFoundException e) {
//			throw new RuntimeException(e);
//		}
//    	
//    	int customerSize = Integer.parseInt(s.nextLine());
//    	List<Customer> customers = new ArrayList<Customer>(customerSize);
//    	while(s.hasNext()) {
//    		String line = s.nextLine();
//    		String tokens[] = line.split(";");
//    		String code = tokens[0];
//    		String fullName[] = tokens[1].split(", ");
//    		String lastName = fullName[0];
//    		String firstName = fullName[1];
//    		String fullAddress[] = tokens[2].split(",");
//    		String street = fullAddress[0];
//    		String city = fullAddress[1];
//    		String state = fullAddress[2];
//    		String zip = fullAddress[3];
//    		String country = fullAddress[4];
//    		String emails[] = {""};
//    		if(tokens.length > 3) {
//        		emails = tokens[3].split(",");
//    		}
//    		
//    		customers.add(new Person(code, lastName, firstName, new Address(street, city, state, zip, country), emails));
//    		
//    	}
//    	s.close();
//    	return customers;
//	}

	public static void main(String[] args) {
		
		List<Person> people = parsePeople();
		
//		List<Customer> customers = parseCustomers();
    	
    	for(Person i : people) {
    		System.out.println(i);
    	}
    	
//    	for(Customer i : customers) {
//    		System.out.println(i);
//    	}

	}

}
