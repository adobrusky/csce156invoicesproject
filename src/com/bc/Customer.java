/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: holds all of the information describing a customer
 */
package com.bc;

public class Customer {
	private String code;
	private char type;
	private String name;
	private Person primaryContact;
	private Address address;
	private double taxRate;
	
	public Customer(String code, char type, String name, Person primaryContact, Address address) {
		this.code = code;
		this.type = type;
		this.name = name;
		this.primaryContact = primaryContact;
		this.address = address;
		
	}

	public String getCode() {
		return code;
	}
	
	public char getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public Person getPrimaryContact() {
		return primaryContact;
	}
	
	public Address getAddress() {
		return address;
	}
	
	
	public double getTaxRate() {
		return taxRate;
	}
	
	@Override
	public String toString() {
		return "Customer [code=" + code + ", type=" + type + ", name=" + name + ", primaryContact=" + primaryContact
				+ ", address=" + address + "]";
	}
}
