package com.bc;

//Customer class holds all the information about a customer
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
		setTaxRate();
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
	
	private void setTaxRate() {
		if(this.type == 'B') {
			this.taxRate = 4.25;
		} else {
			this.taxRate = 8;
		}
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
