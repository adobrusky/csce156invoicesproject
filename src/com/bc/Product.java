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
		return (this.getSubtotal() - this.getDiscount(invoice)) * (taxRate / 100);
	}
	
	public double getTotal(Invoice invoice, double taxRate) {
		return (this.getSubtotal() - this.getDiscount(invoice)) + (this.getSubtotal() - this.getDiscount(invoice)) * (taxRate / 100);
	}
	
	//This is my initial thought on how to do this but seems like alot more work than it should be
//	public double getItemTotalsSubtotal(Invoice invoice) {
//		double ItemTotalsSubtotal = 0;
//		while(invoice.getSubtotal() != 0) {
//			ItemTotalsSubtotal =+ invoice.getSubtotal();
//		}
//		return ItemTotalsSubtotal;
//	}
//	
//	public double getItemTotalsDiscount(Invoice invoice) {
//		double ItemTotalsDiscount = 0;
//		while(invoice.getDiscount() != 0) {
//			ItemTotalsDiscount =+ invoice.getDiscount();
//		}
//		return ItemTotalsDiscount;
//	}
//	
//	public double getItemTotalsTaxes(Invoice invoice) {
//		double ItemTotalsTaxes = 0;
//		while(invoice.getTaxes() != 0) {
//			ItemTotalsTaxes =+ invoice.getTaxes();
//		}
//		return ItemTotalsTaxes;
//	}
//	
//	public double getItemTotalsTotal(Invoice invoice) {
//		double ItemTotalsTaxes = 0;
//		while(invoice.getTotal() != 0) {
//			ItemTotalsTaxes =+ invoice.getTotal();
//		}
//
//		return ItemTotalsTaxes;
//	}
	

	@Override
	public String toString() {
		return "Product [code=" + code + ", type=" + type + ", label=" + label + "]";
	}
	
	
}
