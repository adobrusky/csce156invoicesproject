/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/24/20
 * Description: contains database credentials as well as helper functions to make interacting with the database easier
 */
package com.bc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DBUtil {
	public static final String USERNAME = "adobrusk";
	public static final String PASSWORD = "ekW:t7";
	public static final String URL = "jdbc:mysql://cse.unl.edu/" + USERNAME + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	@SuppressWarnings("unchecked")
	public static <T> T getFromId(String table, int id) {
		//Returns a name from given id for a State or Country, or returns a Product, Person, or Customer object from a given id
		DataSource ds = ConnectionFactory.getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "";
		String column;
		String idColumn = table.toLowerCase() + "Id";

		if(table.equals("State") || table.equals("Country")) {
			column = "name";
		} else {
			column = "code";
		}

		String query = "SELECT " + column + " FROM " + table
				+ " WHERE " + idColumn + " = ?;";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
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

		if(table.equals("Person")) {
			for(Person i : ParsePersons.getPersons()) {
				if(i.getCode().equals(result)) {
					return (T)i;
				}
			}
		} else if(table.equals("Customer")) {
			for(Customer i : ParseCustomers.getCustomers()) {
				if(i.getCode().equals(result)) {
					return (T)i;
				}
			}
		} else if(table.equals("Product")) {
			for(Product i : ParseProducts.getProducts()) {
				if(i.getCode().equals(result)) {
					return (T)i;
				}
			}
		} else {
			return (T)result;
		}
		return null;
	}


	public static Address getAddress(int addressId) {
		//Returns an address based on a given addressId
		
		DataSource ds = ConnectionFactory.getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String street = "";
		String city = "";
		String zip = "";
		int stateId = 0;
		int countryId = 0;

		String query = "SELECT street, city, stateId, zip, countryId FROM Address " + 
				"WHERE addressId = ?;";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, addressId);
			rs = ps.executeQuery();
			while(rs.next()){
				street = rs.getString(1);
				city = rs.getString(2);
				stateId = rs.getInt(3);
				zip = rs.getString(4);
				countryId = rs.getInt(5);
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
		return new Address(street, city, getFromId("State", stateId), zip, getFromId("Country", countryId));
	}


	private static double[] mainProductInfo(String table, int productId, int invoiceId, String props) {
		/* 
		 * Main method that returns product info from a given product id or a product id paired with an invoice id. 
		 * example of product info is the dailyCost or daysRented field in a rental product
		 * */
		
		DataSource ds = ConnectionFactory.getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String propsArr[] = props.split(", ");
		double result[] = new double[propsArr.length];
		String invoiceQuery = "";
		if(table.equals("InvoiceProductList")) {
			invoiceQuery = "AND invoiceId = " + invoiceId;
		}

		String query = "SELECT " + props + " FROM " + table + " "
				+ "WHERE productId = " + productId + " " + invoiceQuery + ";";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				for(int i = 1; i < propsArr.length + 1; i++) {
					result[i - 1] = rs.getDouble(i);
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
		return result;
	}


	public static double[] getProductInfo(String table, int productId, String props) {
		//Runs the mainProductInfo method without a given invoiceId
		
		return mainProductInfo(table, productId, 0, props);
	}

	public static double[] getProductInfo(String table, int productId, int invoiceId, String props) {
		//Runs the mainProductInfo method with a given invoiceId
		
		return mainProductInfo(table, productId, invoiceId, props);
	}
	
	
}
