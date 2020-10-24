/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: parses invoices from the database and stores them as objects
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

	//Creates and returns a list of products from the given invoiceId
	private static List<Product> buildProductList(int invoiceId) {

		List<Product> productList = new ArrayList<Product>();
		int productId = 0;
		double props[];

		DataSource ds = ConnectionFactory.getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT productId FROM InvoiceProductList "
				+ "WHERE invoiceId = " + invoiceId + ";";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				productId = rs.getInt(1);
				Product product = ConnectionFactory.getFromId("Product", productId);
				switch(product.getType()) {
				case 'R':
					props = ConnectionFactory.getProductInfo("InvoiceProductList", productId, invoiceId, "daysRented");
					product = new Rental((Rental)product, props[0]);
					break;
				case 'F':
					props = ConnectionFactory.getProductInfo("InvoiceProductList", productId, invoiceId, "hoursWorked");
					product = new Repair((Repair)product, props[0]);
					break;
				case 'C':
					props = ConnectionFactory.getProductInfo("InvoiceProductList", productId, invoiceId, "quantity, associatedRepair");
					if(props[1] != 0) {
						Product associatedRepair = ConnectionFactory.getFromId("Product", (int)props[1]);
						product = new Concession((Concession)product, props[0], associatedRepair.getCode());
					} else {
						product = new Concession((Concession)product, props[0]);
					}
					break;
				case 'T':
					props = ConnectionFactory.getProductInfo("InvoiceProductList", productId, invoiceId, "milesTowed");
					product = new Towing((Towing)product, props[0]);
					break;
				}
				productList.add(product);

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
		return productList;
	}

	private static List<Invoice> parseInvoices() {
		//Scans info from the Invoice table in the database and parses it into objects of invoice and returns a list of invoices

		int invoiceSize = ConnectionFactory.countTable("Invoice");
		List<Invoice> invoices = new ArrayList<Invoice>(invoiceSize);
		int invoiceId = 0;
		String code = "";
		int ownerId = 0;
		int customerId = 0;
		List<Product> productList = new ArrayList<Product>();

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
				productList = buildProductList(invoiceId);
				invoices.add(new Invoice(code, ConnectionFactory.getFromId("Person", ownerId), ConnectionFactory.getFromId("Customer", customerId), productList));
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
