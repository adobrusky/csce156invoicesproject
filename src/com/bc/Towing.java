package com.bc;

//Towing class holds all of the information regarding a towing product
public class Towing extends Product {
	private double costPerMile;
	private int milesTowed;

	public Towing(String code, char type, String label, double costPerMile) {
		super(code, type, label);
		this.costPerMile = costPerMile;
	}

	public double getCostPerMile() {
		return costPerMile;
	}

	public int getMilesTowed() {
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
			return this.getSubtotal();
		}
		return 0;
	}

	@Override
	public double getTaxes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

}
