package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//ParseProducts class parses the Products.dat and stores the parsed list
public class ParseProducts {
	
	private static List<Product> products = parseProducts();
	
	public static List<Product> getProducts() {
		return products;
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
	
	private static List<Product> parseProducts() {
		//Scans info from Products.dat and parses it into objects of products and returns a list of products
		Scanner s = null;
    	try {
			s = new Scanner(new File("data/ProductsE.dat"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
    	
    	int productSize = Integer.parseInt(s.nextLine());
    	List<Product> products = new ArrayList<Product>(productSize);
    	
    	while(s.hasNext()) {
    		String tokens[] = s.nextLine().split(";");
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
}
