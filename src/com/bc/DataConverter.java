package com.bc;

//DataConverter class outputs the Persons.dat, Customers.dat, and Products.dat as JSON and XML files
public class DataConverter {
	
	public static void main(String[] args) {
		
		//Converts the objects to json and outputs the parsed data into .json files
		Json.write("data/Persons.json", ParsePersons.getPersons(), "persons");
		Json.write("data/Customers.json", ParseCustomers.getCustomers(), "customers");
		Json.write("data/Products.json", ParseProducts.getProducts(), "products");
		
		//Converts the objects to xml and outputs the parsed data into .xml files
		Xml.write("data/Persons.xml", ParsePersons.getPersons(), "persons");
		Xml.write("data/Customers.xml", ParseCustomers.getCustomers(), "customers");
		Xml.write("data/Products.xml", ParseProducts.getProducts(), "products");
	}

}
