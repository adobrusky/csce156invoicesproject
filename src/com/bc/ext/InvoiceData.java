/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/28/20
 * Description: API for interacting with the database
 */
package com.bc.ext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import com.bc.ConnectionFactory;
/* DO NOT change or remove the import statements beneath this.
 * They are required for the webgrader to run this phase of the project.
 * These lines may be giving you an error; this is fine. 
 * These are webgrader code imports, you do not need to have these packages.
 */
import com.bc.model.Concession;
import com.bc.model.Invoice;
import com.bc.model.Customer;
import com.bc.model.Towing;
import com.bc.model.Person;
import com.bc.model.Product;
import com.bc.model.Rental;
import com.bc.model.Repair;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 16 methods in total, add more if required.
 * Do not change any method signatures or the package name.
 * 
 * Adapted from Dr. Hasan's original version of this file
 * @author Chloe
 *
 */


public class InvoiceData {

	//String to make the querying of stuff easier
	private final static String quote = "\", \"";

	private static void insert(String table, String columns, String values) {
		//Inserts values into the db
		
		insertGetKey(table, columns, values);
	}

	private static int insertGetKey(String table, String columns, String values) {
		//Inserts values into the db and returns its primary key or returns a primary key of an item that matches the one being inserted
		
		DataSource ds = ConnectionFactory.getConnectionFactory();
		Connection conn = null;
		PreparedStatement ps = null;
		int key = 0;

		String query = "INSERT INTO " + table + " (" + columns + ") "
				+ "VALUES(\"" + values + "\");";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			keys.next();
			key = keys.getInt(1);
			keys.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return key;

	}
	
	private static void delete(String table) {
		//Deletes all rows of a given table
		
		DataSource ds = ConnectionFactory.getConnectionFactory();
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM " + table + ";";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}


	private static int getId(String table, String field, String data) {
		//Get the primary key of an item that matches that criteria and if nothing matches it inserts it and returns the primary key
		
		DataSource ds = ConnectionFactory.getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String columnId = table.toLowerCase() + "Id";
		Integer id = 0;

		String query = "SELECT " + columnId + " FROM " + table + " " + 
				"WHERE " + field + " = \"" + data + "\";";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next() != false) {
				id = rs.getInt(1);
			} else {
				id = insertGetKey(table, field, data);
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

		return id;
	}

	private static int getAddressId(String street, String city, String state, String zip, String country) {
		//Returns the primary key of an address that matches the given criteria. If nothing matches it will insert it and return the key
		
		DataSource ds = ConnectionFactory.getConnectionFactory();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int stateId = getId("State", "name", state);
		int countryId = getId("Country", "name", country);
		Integer addressId = 0;

		String query = "SELECT addressId FROM Address " + 
				"WHERE street = \"" + street + "\" AND "
				+ "city = \"" + city + "\" AND "
				+ "stateId = \"" + stateId + "\" AND "
				+ "countryId = \"" + countryId + "\";";

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next() != false) {
				addressId = rs.getInt(1);
			} else {
				addressId = insertGetKey("Address", "street, city, stateId, zip, countryId", street + quote + city
						+ quote + stateId + quote + zip + quote + countryId);
			}
			while(rs.next()){
				addressId = rs.getInt(1);
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

		return addressId;
	}

	/**
	 * 1. Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		delete("Email");
		removeAllCustomers();
		delete("Person");
	}

	/**
	 * 2. Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country) {
		int addressId = getAddressId(street, city, state, zip, country);
		insert("Person", "code, firstName, lastName, addressId", personCode + quote + firstName + quote + 
				lastName + quote + addressId);
	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		insert("Email", "personId, address", personCode + quote + email);
	}

	/**
	 * 4. Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() {
		removeAllInvoices();
		delete("Customer");
	}

	/**
	 * 5. Method to add a customer record to the database with the provided data
	 * 
	 * @param customerCode
	 * @param customerType
	 * @param primaryContactPersonCode
	 * @param name
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addCustomer(String customerCode, String customerType, String primaryContactPersonCode, String name, String street, String city, String state, String zip, String country) {
		int addressId = getAddressId(street, city, state, zip, country);
		int primaryContactId = getId("Person", "code", primaryContactPersonCode);
		insert("Customer", "code, type, name, primaryContact, addressId", customerCode + quote + customerType + quote + 
				name + quote + primaryContactId + quote + addressId);
	}

	/**
	 * 6. Removes all product records from the database
	 */
	public static void removeAllProducts() {
		delete("InvoiceProductList");
		delete("Product");
	}

	/**
	 * 7. Adds a concession record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param productLabel
	 * @param unitCost
	 */
	public static void addConcession(String productCode, String productLabel, double unitCost) {
		insert("Product", "code, type, label, unitCost", productCode + "\", \"C\", \"" + productLabel + quote + unitCost);
	}

	/**
	 * 8. Adds a repair record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param proudctLabel
	 * @param partsCost
	 * @param laborRate
	 */
	public static void addRepair(String productCode, String productLabel, double partsCost, double laborRate) {
		insert("Product", "code, type, label, partsCost, hourlyLaborCosts", productCode + "\", \"F\", \"" + productLabel + quote + partsCost + quote + laborRate);
	}

	/**
	 * 9. Adds a towing record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param productLabel
	 * @param costPerMile
	 */
	public static void addTowing(String productCode, String productLabel, double costPerMile) {
		insert("Product", "code, type, label, costPerMile", productCode + "\", \"T\", \"" + productLabel + quote + costPerMile);
	}

	/**
	 * 10. Adds a rental record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param productLabel
	 * @param dailyCost
	 * @param deposit
	 * @param cleaningFee
	 */
	public static void addRental(String productCode, String productLabel, double dailyCost, double deposit, double cleaningFee) {
		insert("Product", "code, type, label, dailyCost, deposit, cleaningFee", productCode + "\", \"R\", \"" + productLabel + quote + dailyCost + quote + deposit + quote + cleaningFee);
	}

	/**
	 * 11. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
		delete("InvoiceProductList");
		delete("Invoice");
	}

	/**
	 * 12. Adds an invoice record to the database with the given data.
	 * 
	 * @param invoiceCode
	 * @param ownerCode
	 * @param customertCode
	 */
	public static void addInvoice(String invoiceCode, String ownerCode, String customerCode) {
		int ownerId = getId("Person", "code", ownerCode);
		int customerId = getId("Customer", "code", customerCode);
		insert("Invoice", "invoiceCode, ownerCode, customerCode", invoiceCode + quote + ownerId + quote + customerId);
	}

	/**
	 * 13. Adds a particular Towing (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of miles towed
	 * 
	 * @param invoiceCode
	 * @param productCode
	 * @param milesTowed
	 */
	public static void addTowingToInvoice(String invoiceCode, String productCode, double milesTowed) {
		int invoiceId = getId("Invoice", "invoiceCode", invoiceCode);
		int productId = getId("Product", "code", productCode);
		insert("InvoiceProductList", "invoiceId, productId, milesTowed", invoiceId + quote + productId + quote + milesTowed);
	}

	/**
	 * 14. Adds a particular Repair (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of hours worked
	 * 
	 * @param invoiceCode
	 * @param productCode
	 * @param hoursWorked
	 */
	public static void addRepairToInvoice(String invoiceCode, String productCode, double hoursWorked) {
		int invoiceId = getId("Invoice", "invoiceCode", invoiceCode);
		int productId = getId("Product", "code", productCode);
		insert("InvoiceProductList", "invoiceId, productId, hoursWorked", invoiceId + quote + productId + quote + hoursWorked);
	}

	/**
	 * 15. Adds a particular Concession (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 * NOTE: repairCode may be null
	 * 
	 * @param invoiceCode
	 * @param productCode
	 * @param quantity
	 * @param repairCode
	 */
	public static void addConcessionToInvoice(String invoiceCode, String productCode, int quantity, String repairCode) {
		int invoiceId = getId("Invoice", "invoiceCode", invoiceCode);
		int productId = getId("Product", "code", productCode);
		if(repairCode.length() > 0) {
			int repairId = getId("Product", "code", repairCode);
			insert("InvoiceProductList", "invoiceId, productId, quantity, associatedRepair", invoiceId + quote + productId + quote + quantity + quote + repairId);
		} else {
			insert("InvoiceProductList", "invoiceId, productId, quantity", invoiceId + quote + productId + quote + quantity);
		}

	}

	/**
	 * 16. Adds a particular Rental (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of days rented. 
	 * 
	 * @param invoiceCode
	 * @param productCode
	 * @param daysRented
	 */
	public static void addRentalToInvoice(String invoiceCode, String productCode, double daysRented) {
		int invoiceId = getId("Invoice", "invoiceCode", invoiceCode);
		int productId = getId("Product", "code", productCode);
		insert("InvoiceProductList", "invoiceId, productId, daysRented", invoiceId + quote + productId + quote + daysRented);
	}


}
