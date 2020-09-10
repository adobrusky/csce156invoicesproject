package com.bc;

public class Towing extends Product {
	private double costPerMile;

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

	@Override
	public String toString() {
		return super.toString() + "Towing [costPerMile=" + costPerMile + "]";
	}
	

}
