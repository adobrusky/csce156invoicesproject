package com.bc;

public class Concession extends Product {

	private double unitCost;
	private int quanity;
	private String associatedRepair;

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

	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}

	public String getAssociatedRepair() {
		return associatedRepair;
	}

	public void setAssociatedRepair(String associatedRepair) {
		this.associatedRepair = associatedRepair;
	}

	@Override
	public String toString() {
		return "Concession [unitCost=" + unitCost + ", quanity=" + quanity + ", associatedRepair=" + associatedRepair
				+ "]";
	}
	
}
