package com.bc;

//Product class holds all of the information regarding a product
public abstract class Product {
	private String code;
	private char type;
	private String label;
	
	public Product(String code, char type, String label) {
		this.code = code;
		this.type = type;
		this.label = label;
	}

	public String getCode() {
		return code;
	}
	
	public char getType() {
		return type;
	}

	public String getLabel() {
		return label;
	}
	

	public abstract double getDiscount(Invoice invoice);
	
	public abstract double getSubtotal();
	
	public double getTaxes(Invoice invoice, double taxRate) {
		return (this.getSubtotal() + this.getDiscount(invoice)) * (taxRate / 100);
	}
	
	public double getTotal(Invoice invoice, double taxRate) {
		return (this.getSubtotal() + this.getDiscount(invoice)) + (this.getSubtotal() + this.getDiscount(invoice)) * (taxRate / 100);
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", type=" + type + ", label=" + label + "]";
	}
	
	
}
