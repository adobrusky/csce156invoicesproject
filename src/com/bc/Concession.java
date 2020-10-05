/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: subclass of product class and describes concession product
 */
package com.bc;

public class Concession extends Product {

	private double unitCost;
	private double quanity;
	private String associatedRepair = null;

	public Concession(String code, char type, String label, double unitCost) {
		super(code, type, label);
		this.unitCost = unitCost;
	}
	
	//Copy Constructors
	public Concession(Concession old, double quanity) {
		super(old.getCode(), old.getType(), old.getLabel());
		this.unitCost = old.getUnitCost();
		this.quanity = quanity;
	}
	
	public Concession(Concession old, double quanity, String associatedRepair) {
		super(old.getCode(), old.getType(), old.getLabel());
		this.unitCost = old.getUnitCost();
		this.quanity = quanity;
		this.associatedRepair = associatedRepair;
	}

	public double getUnitCost() {
		return unitCost;
	}
	
	public double getQuanity() {
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
