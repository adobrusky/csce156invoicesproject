package com.bc;

import java.util.List;

//Invoice class holds all the information for an invoice
public class Invoice implements Comparable<Invoice> {
	
	private String invoiceCode;
	private Person owner;
	private Customer customer;
	private List<Product>listOfProducts;
	
	public Invoice(String invoiceCode, Person owner, Customer customer, List<Product> listOfProducts) {
		this.invoiceCode = invoiceCode;
		this.owner = owner;
		this.customer = customer;
		this.listOfProducts = listOfProducts;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public Person getOwner() {
		return owner;
	}

	public Customer getCustomer() {
		return customer;
	}

	public List<Product> getListOfProducts() {
		return listOfProducts;
	}

	@Override
	public String toString() {
		return "Invoice [invoiceCode=" + invoiceCode + ", owner=" + owner + ", customer=" + customer
				+ ", listOfProducts=" + listOfProducts + "]";
	}

	@Override
	public int compareTo(Invoice other) {
		return getCustomer().getName().compareTo(other.getCustomer().getName());
	}
	
}
