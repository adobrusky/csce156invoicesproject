package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PortfolioReport {
	
	public static Product findProduct(String productCode) {
		//Find the product from the given code
		for(Product i : DataConverter.parseProducts()) {
			if(i.getCode().equals(productCode)) {
				return i;
			}
		}
		return null;
	}
	
	public static Person findOwner(String ownerCode) {
		//Find the person from the given code
		for(Person i : DataConverter.parsePersons()) {
			if(i.getCode().equals(ownerCode)) {
				return i;
			}
		}
		return null;
	}
	
	public static Customer findCustomer(String customerCode) {
		//Find the customer from the given code
		for(Customer i : DataConverter.parseCustomers(DataConverter.parsePersons())) {
			if(i.getCode().equals(customerCode)) {
				return i;
			}
		}
		return null;
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
    		invoices.add(new Invoice(invoiceCode, findOwner(ownerCode), findCustomer(customerCode), productList));
    		
    	}
    	
    	s.close();
    	return invoices;
	}

	public static void main(String[] args) {
		
		//Creates a list of invoices from the Invoices.dat
		List<Invoice> invoices = parseInvoices();
		
		//Print the summary report
		System.out.println(SummaryReport.print(invoices));
		
	}

}
