package com.bc;

import java.util.Arrays;

public class Customer {
	private String code;
	private char type;
	private String name;
	private Person primaryContact;
	private String[] emails;
	
	public Customer(String c, char t, String n, Person p, String[] e) {
		setCode(c);
		setType(t);
		setName(n);
		setPrimaryContact(p);
		setEmails(e);
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
	
	public String[] getEmails() {
		return emails;
	}
	
	public void setEmails(String[] emails) {
		this.emails = emails;
	}
	
	@Override
	public String toString() {
		return "Customer [code=" + code + ", type=" + type + ", name=" + name + ", primaryContact=" + primaryContact
				+ ", emails=" + Arrays.toString(emails) + "]";
	}
}
