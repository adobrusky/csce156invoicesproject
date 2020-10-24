/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/24/20
 * Description: holds some main database connection methods that are used across several classes
 */
package com.sf.ext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.bc.Address;
import com.bc.Customer;
import com.bc.ParseCustomers;
import com.bc.ParsePersons;
import com.bc.ParseProducts;
import com.bc.Person;
import com.bc.Product;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionFactory {

	//Main connection factory method. Sets up the connection with credentials from DBUtil.java
	public static DataSource getConnectionFactory() {
		MysqlDataSource mysqlDS = null;
		mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(DBUtil.URL);
		mysqlDS.setUser(DBUtil.USERNAME);
		mysqlDS.setPassword(DBUtil.PASSWORD);
		return mysqlDS;
	}

	//Counts the amount of items in a table
	public static int countTable(String tableName) {

		DataSource ds = getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT COUNT(*) FROM " + tableName + ";";
		int tableSize = 0;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				tableSize = rs.getInt(1);
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
		return tableSize;

	}


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
				+ " WHERE " + idColumn + " = " + id + ";";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
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

	//Returns an address based on a given addressId
	public static Address getAddress(int addressId) {

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
				"WHERE addressId = " + addressId + ";";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
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
		return new Address(street, city, ConnectionFactory.getFromId("State", stateId), zip, ConnectionFactory.getFromId("Country", countryId));
	}

	/* 
	 * Main method that returns product info from a given product id or a product id paired with an invoice id. 
	 * example of product info is the dailyCost or daysRented field in a rental product
	 * */
	private static double[] mainProductInfo(String table, int productId, int invoiceId, String props) {
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

	//Runs the mainProductInfo method without a given invoiceId
	public static double[] getProductInfo(String table, int productId, String props) {
		return mainProductInfo(table, productId, 0, props);
	}

	//Runs the mainProductInfo method with a given invoiceId
	public static double[] getProductInfo(String table, int productId, int invoiceId, String props) {
		return mainProductInfo(table, productId, invoiceId, props);
	}

}