/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: holds all of the information for an invoice
 */
package com.bc;

import java.util.List;

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

	public double getSubtotal() {
		double subtotal = 0;
		for(Product i : this.listOfProducts) {
			subtotal += i.getSubtotal();
		}
		return subtotal;
	}
	
	public double getDiscount() {
		double discount = 0;
		for(Product i : this.listOfProducts) {
			discount += i.getDiscount(this);
		}
		discount += this.getLoyaltyDiscount();
		return discount;
	}
	
	public double getTaxes() {
		double taxes = 0;
		for(Product i : this.listOfProducts) {
			taxes += i.getTaxes(this, this.getCustomer().getTaxRate());
		}
		return taxes;
	}
	
	public double getFees() {
		if(this.customer.getType() == 'B') {
			return 75.50;
		} else {
			return 0;
		}
	}
	
	public double getTotal() {
		double total = this.getFees();
		for(Product i : this.listOfProducts) {
			total += i.getTotal(this, this.getCustomer().getTaxRate());
		}
		return total;
	}
	
	public double getLoyaltyDiscount() {
		if(this.getCustomer().getType() == 'P' && this.getCustomer().getPrimaryContact().getEmails().length >= 2) {
			return (this.getTotal() - this.getFees()) * -0.05;
		} else {
			return 0;
		}
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
