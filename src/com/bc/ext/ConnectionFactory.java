package com.bc.ext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
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
	
	public static String getGeoName(String table, int geoId) {

		DataSource ds = getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String geoColumn = "";
		String geoName = "";
		
		if(table.equals("State")) {
			geoColumn = "stateId";
		} else if(table.equals("Country")){
			geoColumn = "countryId";
		}

		String query = "SELECT name FROM " + table + " "
				+ "WHERE " + geoColumn + " = " + geoId + ";";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				geoName = rs.getString(1);
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
		return geoName;

	}

}