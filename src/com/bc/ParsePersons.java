/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: parses persons from the database and stores them as objects
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

public class ParsePersons {
	
	private static List<Person> persons = parsePersons();
	
	public static List<Person> getPersons() {
		return persons;
	}
	
	//returns an array of emails from a given personId
	public static String[] getEmails(int personId) {

		DataSource ds = ConnectionFactory.getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String emails = "";

		String query = "SELECT address FROM Email " + 
				"WHERE personId = " + personId + ";";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				emails += rs.getString(1) + ",";
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

		return emails.split(",");
	}
	
	private static List<Person> parsePersons() {
		//Scans info from the Person table in the database and parses it into objects of people and returns a list of people

    	int peopleSize = ConnectionFactory.countTable("Person");
    	List<Person> people = new ArrayList<Person>(peopleSize);
    	String code = "";
		String lastName = "";
		String firstName = "";
		int addressId = 0;
		int personId = 0;
    	
    	DataSource ds = ConnectionFactory.getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM Person;";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				personId = rs.getInt(1);
				code = rs.getString(2);
				lastName = rs.getString(3);
				firstName = rs.getString(4);
				addressId = rs.getInt(5);
				people.add(new Person(code, firstName, lastName, ConnectionFactory.getAddress(addressId), getEmails(personId)));
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
    	return people;
	}
}
