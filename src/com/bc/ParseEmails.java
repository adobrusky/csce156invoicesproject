package com.bc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.sf.ext.ConnectionFactory;

public class ParseEmails {

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
}
