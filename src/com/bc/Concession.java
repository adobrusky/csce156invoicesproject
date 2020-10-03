package com.bc;

//Concession class represents a concession product
public class Concession extends Product {

	private double unitCost;
	private int quanity;
	private String associatedRepair = null;

	public Concession(String code, char type, String label, double unitCost) {
		super(code, type, label);
		this.unitCost = unitCost;
	}
	
	//Copy Constructors
	public Concession(Concession old, int quanity) {
		super(old.getCode(), old.getType(), old.getLabel());
		this.unitCost = old.getUnitCost();
		this.quanity = quanity;
	}
	
	public Concession(Concession old, int quanity, String associatedRepair) {
		super(old.getCode(), old.getType(), old.getLabel());
		this.unitCost = old.getUnitCost();
		this.quanity = quanity;
		this.associatedRepair = associatedRepair;
	}

	public double getUnitCost() {
		return unitCost;
	}
	
	public int getQuanity() {
		return quanity;
	}

	public String getAssociatedRepair() {
		return associatedRepair;
	}

	@Override
	public double getSubtotal() {
		double total = (getQuanity()*unitCost);
		return total;
	}

	@Override
	public String toString() {
		return "Concession [unitCost=" + unitCost + ", quanity=" + quanity + ", associatedRepair=" + associatedRepair
				+ "]";
	}

	@Override
	public double getDiscount(Invoice invoice) {
		if(this.getAssociatedRepair() != null) {
			return -(getSubtotal() * 0.1);
		}
		return 0;
	}
	
}
