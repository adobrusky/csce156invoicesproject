package com.bc;

import java.util.Arrays;

public class Person {
	private String code;
	private String firstName;
	private String lastName;
	private Address Address;
	private String[] emails;
	
	public Person(String c, String l, String f, Address a, String[] e) {
		setCode(c);
		setLastName(l);
		setFirstName(f);
		setAddress(a);
		setEmails(e);
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public Address getAddress() {
		return Address;
	}
	
	public void setAddress(Address address) {
		this.Address = address;
	}
	
	public String[] getEmails() {
		return emails;
	}
	
	public void setEmails(String[] emails) {
		this.emails = emails;
	}
	
	@Override
	public String toString() {
		return "Person [code=" + code + ", lastName=" + lastName + ", firstName=" + firstName + ", address=" + Address
				+ ", emails=" + Arrays.toString(emails) + "]";
	}
}
