package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataConverter {

	public static void main(String[] args) {
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
    	
    	for(Person i : people) {
    		System.out.println(i);
    	}

	}

}
