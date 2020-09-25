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
	public String toString() {
		return "Towing [costPerMile=" + costPerMile + ", milesTowed=" + milesTowed + "]";
	}

}
