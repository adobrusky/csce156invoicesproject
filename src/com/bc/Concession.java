package com.bc;

public class Concession extends Product {

	private double unitCost;

	public Concession(String c, char t, String l, double u) {
		super(c, t, l);
		setUnitCost(u);
	}
	
	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	@Override
	public String toString() {
		return super.toString() + "Concession [unitCost=" + unitCost + "]";
	}
	
}
