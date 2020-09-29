package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//ParsePersons class parses the Persons.dat and stores the parsed list
public class ParsePersons {
	
	private static List<Person> persons = parsePersons();
	
	//Getter function
	public static List<Person> getPersons() {
		return persons;
	}
	
	public static Person findPerson(String code) {
		//Finds a person based on the given code
		for(Person i : persons) {
			if(i.getCode().equals(code)) {
				return i;
			}
		}
		return null;
	}
	
	private static List<Person> parsePersons() {
		//Scans info from Persons.dat and parses it into objects of people and returns a list of people
		Scanner s = null;
    	try {
			s = new Scanner(new File("data/PersonsE.dat"));
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
    		
    		people.add(new Person(code, firstName, lastName, new Address(street, city, state, zip, country), emails));	
    	}
    	
    	s.close();
    	return people;
	}
}
