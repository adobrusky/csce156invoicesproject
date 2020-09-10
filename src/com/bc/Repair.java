package com.bc;

public class Repair extends Product {
	
	private double partsCost;
	private double hourlyLaborCost;

	public Repair(String c, char t, String l, double p, double h) {
		super(c, t, l);
		setPartsCost(p);
		setHourlyLaborCost(h);
	}

	public double getPartsCost() {
		return partsCost;
	}

	public void setPartsCost(double partsCost) {
		this.partsCost = partsCost;
	}

	public double getHourlyLaborCost() {
		return hourlyLaborCost;
	}

	public void setHourlyLaborCost(double hourlyLaborCost) {
		this.hourlyLaborCost = hourlyLaborCost;
	}

	@Override
	public String toString() {
		return super.toString() + "Repair [partsCost=" + partsCost + ", hourlyLaborCost=" + hourlyLaborCost + "]";
	}
	
	

}
