package com.sf.ext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.bc.Customer;
import com.bc.ParseCustomers;
import com.bc.ParsePersons;
import com.bc.ParseProducts;
import com.bc.Person;
import com.bc.Product;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionFactory {

	public static DataSource getConnectionFactory() {
		MysqlDataSource mysqlDS = null;
		mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(DBUtil.URL);
		mysqlDS.setUser(DBUtil.USERNAME);
		mysqlDS.setPassword(DBUtil.PASSWORD);
		return mysqlDS;
	}

	public static int getTableSize(String tableName) {

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
	public static <T> T getFromId(String table, String id) {
		//Finds a given code, name from a given Product, Person, Customer, State, Country table based on id
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

}