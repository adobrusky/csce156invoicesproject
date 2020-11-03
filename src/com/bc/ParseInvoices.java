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

public class ParseInvoices {
	private static InvoiceList<Invoice> invoices = parseInvoices();

	public static InvoiceList<Invoice> getInvoices() {
		return invoices;
	}

	private static List<Product> buildProductList(int invoiceId) {
		//Creates and returns a list of products from the given invoiceId

		List<Product> productList = new ArrayList<Product>();
		int productId = 0;
		double props[];

		DataSource ds = ConnectionFactory.getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT productId FROM InvoiceProductList "
				+ "WHERE invoiceId = ?;";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceId);
			rs = ps.executeQuery();
			while(rs.next()){
				productId = rs.getInt(1);
				Product product = DBUtil.getFromId("Product", productId);
				switch(product.getType()) {
				case 'R':
					props = DBUtil.getProductInfo("InvoiceProductList", productId, invoiceId, "daysRented");
					product = new Rental((Rental)product, props[0]);
					break;
				case 'F':
					props = DBUtil.getProductInfo("InvoiceProductList", productId, invoiceId, "hoursWorked");
					product = new Repair((Repair)product, props[0]);
					break;
				case 'C':
					props = DBUtil.getProductInfo("InvoiceProductList", productId, invoiceId, "quantity, associatedRepair");
					if(props[1] != 0) {
						Product associatedRepair = DBUtil.getFromId("Product", (int)props[1]);
						product = new Concession((Concession)product, props[0], associatedRepair.getCode());
					} else {
						product = new Concession((Concession)product, props[0]);
					}
					break;
				case 'T':
					props = DBUtil.getProductInfo("InvoiceProductList", productId, invoiceId, "milesTowed");
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

	private static InvoiceList<Invoice> parseInvoices() {
		//Scans info from the Invoice table in the database and parses it into objects of invoice and returns a list of invoices

		InvoiceList<Invoice> invoices = new InvoiceList<Invoice>();
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
				invoices.add(new Invoice(code, DBUtil.getFromId("Person", ownerId), DBUtil.getFromId("Customer", customerId), productList));
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
		return invoices;
	}
}
