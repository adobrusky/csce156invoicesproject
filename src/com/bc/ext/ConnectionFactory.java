package com.bc.ext;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionFactory {

	public static DataSource getConnectionFactory() {
		Properties props = new Properties();
		FileInputStream file = null;
		MysqlDataSource mysqlDS = null;
		try {
			file = new FileInputStream("database/db.properties");
			props.load(file);
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
			mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
			mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mysqlDS;
	}
		
}