/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/24/20
 * Description: Contains the connection factory
 */
package com.bc;

import javax.sql.DataSource;

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

}