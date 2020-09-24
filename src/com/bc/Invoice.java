package com.bc;

import java.util.List;

public class Invoice {
	
	private String invoiceCode;
	private Person owner;
	private Customer customer;
	private List<Product>listOfProducts;
	
	public Invoice(String invoiceCode, Person owner, Customer customer, List<Product> listOfProducts) {
		setInvoiceCode(invoiceCode);
		setOwner(owner);
		setCustomer(customer);
		setListOfProducts(listOfProducts);
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Product> getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(List<Product> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

	@Override
	public String toString() {
		return "Invoice [invoiceCode=" + invoiceCode + ", owner=" + owner + ", customer=" + customer
				+ ", listOfProducts=" + listOfProducts + "]";
	}
	
	
	

}
