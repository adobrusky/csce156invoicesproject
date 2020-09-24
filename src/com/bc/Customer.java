package com.bc;

public class Customer {
	private String code;
	private char type;
	private String name;
	private Person primaryContact;
	private Address address;
	private double taxRate;
	
	public Customer(String c, char t, String n, Person p, Address a) {
		setCode(c);
		setType(t);
		setName(n);
		setPrimaryContact(p);
		setAddress(a);
		setTaxRate();
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public char getType() {
		return type;
	}
	
	public void setType(char type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Person getPrimaryContact() {
		return primaryContact;
	}
	
	public void setPrimaryContact(Person primaryContact) {
		this.primaryContact = primaryContact;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void setTaxRate() {
		if(this.type == 'B') {
			taxRate = 4.25;
		} else {
			taxRate = 8;
		}
	}
	
	@Override
	public String toString() {
		return "Customer [code=" + code + ", type=" + type + ", name=" + name + ", primaryContact=" + primaryContact
				+ ", address=" + address + "]";
	}
}
