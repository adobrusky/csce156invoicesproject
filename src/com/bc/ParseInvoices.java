/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: parses the Invoices.dat and stores the parsed list
 */
package com.bc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.sf.ext.ConnectionFactory;

public class ParseInvoices {
	private static List<Invoice> invoices = parseInvoices();
	
	public static List<Invoice> getInvoices() {
		return invoices;
	}
	
	private static int countProducts(int invoiceId) {
		DataSource ds = ConnectionFactory.getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT COUNT(*) FROM InvoiceProductList "
				+ "WHERE invoiceId = " + invoiceId + ";";
		int result = 0;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private static List<Invoice> parseInvoices() {
		//Scans info from the Invoice table in the database and parses it into objects of invoice and returns a list of invoices
		
		int invoiceSize = ConnectionFactory.getTableSize("Invoice");
    	List<Invoice> invoices = new ArrayList<Invoice>(invoiceSize);
    	int invoiceId = 0;
    	String code = "";
		int ownerId = 0;
		int customerId = 0;
    	
    	DataSource ds = ConnectionFactory.getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM Invoice;";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				invoiceId = rs.getInt(1);
				code = rs.getString(2);
				ownerId = rs.getInt(3);
				customerId = rs.getInt(4);
				
				for(int i = 0; i < countProducts(invoiceId); i++) {
					
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/*
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
    			Product product = ParseProducts.findProduct(productInfo[0]);
    			for(int j = 0; j < productInfo.length; j++) {
    				switch(product.getType()) {
    					case 'R':
    						product = new Rental((Rental)product, Double.parseDouble(productInfo[1]));
    						break;
    					case 'F':
    						product = new Repair((Repair)product, Double.parseDouble(productInfo[1]));
    						break;
    					case 'C':
    						if(productInfo.length == 3) {
        						product = new Concession((Concession)product, Double.parseDouble(productInfo[1]), productInfo[2]);
    						} else {
    							product = new Concession((Concession)product, Double.parseDouble(productInfo[1]));
    						}
    						break;
    					case 'T':
    						product = new Towing((Towing)product, Double.parseDouble(productInfo[1]));
    						break;
    				}
    				
    			}
    			productList.add(product);
    		}
    		invoices.add(new Invoice(invoiceCode, ParsePersons.findPerson(ownerCode), ParseCustomers.findCustomer(customerCode), productList));
    		
    	}
    	
    	s.close(); */
    	return invoices;
	}
}
