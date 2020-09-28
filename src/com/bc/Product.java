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
	

	
	public double getPersonalTaxRate(double taxable) {		
		double tax = taxable*.08;
		return tax;
	}
	
	public double getBusinessTaxRate(double taxable) {
		double tax = taxable*.0425;
		return tax;
	}
	
	public abstract double getSubtotal();
	
	@Override
	public String toString() {
		return "Product [code=" + code + ", type=" + type + ", label=" + label + "]";
	}
	
	
}
