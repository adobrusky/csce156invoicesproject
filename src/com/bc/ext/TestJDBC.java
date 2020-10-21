package com.bc.ext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class TestJDBC {

	public static void main(String[] args) {
		
		DataSource ds = null;
		ds = ConnectionFactory.getConnectionFactory();

		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT code, lastName from Person");
			while(rs.next()){
				System.out.println("Code="+rs.getString("code") + ", lastName=" + rs.getString("lastName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

}