package com.bc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.bc.ext.ConnectionFactory;

public class ParseAddress {
	
	public static Address getAddress(int addressId) {
		
		DataSource ds = ConnectionFactory.getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String street = "";
		String city = "";
		int stateId = 0;
		String zip = "";
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
		return new Address(street, city, ConnectionFactory.getGeoName("State", stateId), zip, ConnectionFactory.getGeoName("Country", countryId));
	}
	
}
