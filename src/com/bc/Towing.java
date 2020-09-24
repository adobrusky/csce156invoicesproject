package com.bc;

public class Towing extends Product {
	private double costPerMile;
	private int milesTowed;

	public Towing(String c, char t, String l, double co) {
		super(c, t, l);
		setCostPerMile(co);
	}

	public double getCostPerMile() {
		return costPerMile;
	}

	public void setCostPerMile(double costPerMile) {
		this.costPerMile = costPerMile;
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
