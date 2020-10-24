/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: parses products from the database and stores them as objects
 */
package com.bc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ParseProducts {

	private static List<Product> products = parseProducts();

	public static List<Product> getProducts() {
		return products;
	}

	private static List<Product> parseProducts() {
		//Scans info from the Product table in the database and parses it into objects of products and returns a list of products

		int productSize = DBUtil.countTable("Product");
		List<Product> products = new ArrayList<Product>(productSize);
		int productId = 0;
		String code = "";
		char type;
		String label = "";
		double props[];

		DataSource ds = ConnectionFactory.getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT productId, code, type, label FROM Product;";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				productId = rs.getInt(1);
				code = rs.getString(2);
				type = rs.getString(3).charAt(0);
				label = rs.getString(4);
				
				switch(type) {
				case 'R':
					props = DBUtil.getProductInfo("Product", productId, "dailyCost, deposit, cleaningFee");
					products.add(new Rental(code, type, label, props[0], props[1], props[2]));
					break;
				case 'F':
					props = DBUtil.getProductInfo("Product", productId, "partsCost, hourlyLaborCosts");
					products.add(new Repair(code, type, label, props[0], props[1]));
					break;
				case 'C':
					props = DBUtil.getProductInfo("Product", productId, "unitCost");
					products.add(new Concession(code, type, label, props[0]));
					break;
				case 'T':
					props = DBUtil.getProductInfo("Product", productId, "costPerMile");
					products.add(new Towing(code, type, label, props[0]));
					break;
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
		
		return products;
	}
}
