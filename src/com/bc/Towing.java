/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: holds all of the information regarding a towing product
 */
package com.bc;

public class Towing extends Product {
	private double costPerMile;
	private double milesTowed;

	public Towing(String code, char type, String label, double costPerMile) {
		super(code, type, label);
		this.costPerMile = costPerMile;
	}
	
	//Copy Constructor
	public Towing(Towing old, double milesTowed) {
		super(old.getCode(), old.getType(), old.getLabel());
		this.costPerMile = old.getCostPerMile();
		this.milesTowed = milesTowed;
	}

	public double getCostPerMile() {
		return costPerMile;
	}

	public double getMilesTowed() {
		return milesTowed;
	}

	public void setMilesTowed(int milesTowed) {
		this.milesTowed = milesTowed;
	}

	@Override
	public double getSubtotal() {
		
		double total = costPerMile*milesTowed;
		return total;
	}
	
	@Override
	public String toString() {
		return "Towing [costPerMile=" + costPerMile + ", milesTowed=" + milesTowed + "]";
	}

	public double getDiscount(Invoice invoice) {
		String discountCode = "";
		for(Product i : invoice.getListOfProducts()) {
			discountCode += i.getType();
		}
		if(discountCode.contains("R") && discountCode.contains("F") && discountCode.contains("T")) {
			return this.getSubtotal() * -1;
		}
		return 0;
	}
}
