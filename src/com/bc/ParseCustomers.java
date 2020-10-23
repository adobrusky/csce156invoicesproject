/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: parses the Cutomers.dat and stores the parsed list
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

public class ParseCustomers {
	
	private static List<Customer> customers = parseCustomers();
	
	public static List<Customer> getCustomers() {
		return customers;
	}
	
	private static List<Customer> parseCustomers() {
		//Scans info from the Customer table in the database and parses it into objects of Customer and returns a list of Customers

    	int customerSize = ConnectionFactory.getTableSize("Customer");
    	List<Customer> customers = new ArrayList<Customer>(customerSize);
    	String code = "";
		char type;
		String name = "";
		String primaryContact = "";
		int addressId = 0;
    	
    	DataSource ds = ConnectionFactory.getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT code, type, name, primaryContact, addressId FROM Customer;";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				code = rs.getString(1);
				type = rs.getString(2).charAt(0);
				name = rs.getString(3);
				primaryContact = rs.getString(4);
				addressId = rs.getInt(5);
				customers.add(new Customer(code, type, name, ConnectionFactory.getFromId("Person", primaryContact), ParseAddress.getAddress(addressId)));
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
    	return customers;
    	
	}
}
