/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: holds all of the information regarding a person
 */
package com.bc;

import java.util.Arrays;

public class Person {
	private String code;
	private String firstName;
	private String lastName;
	private Address Address;
	private String[] emails;
	
	public Person(String code, String firstName, String lastName, com.bc.Address address, String[] emails) {
		this.code = code;
		this.firstName = firstName;
		this.lastName = lastName;
		Address = address;
		this.emails = emails;
	}

	public String getCode() {
		return code;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public Address getAddress() {
		return Address;
	}
	
	public String[] getEmails() {
		return emails;
	}
	
	@Override
	public String toString() {
		return "Person [code=" + code + ", lastName=" + lastName + ", firstName=" + firstName + ", address=" + Address
				+ ", emails=" + Arrays.toString(emails) + "]";
	}
}
