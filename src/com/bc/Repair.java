package com.bc;

//Repair class holds all of the information regarding a repair product
public class Repair extends Product {
	
	private double partsCost;
	private double hourlyLaborCost;
	private double hoursWorked;

	public Repair(String code, char type, String label, double partsCost, double hourlyLaborCost) {
		super(code, type, label);
		this.partsCost = partsCost;
		this.hourlyLaborCost = hourlyLaborCost;
	}
	
	//Copy Constructor
	public Repair(Repair old, double d) {
		super(old.getCode(), old.getType(), old.getLabel());
		this.partsCost = old.getPartsCost();
		this.hourlyLaborCost = old.getHourlyLaborCost();
		this.hoursWorked = d;
		
	}

	public double getPartsCost() {
		return partsCost;
	}

	public double getHourlyLaborCost() {
		return hourlyLaborCost;
	}
	
	public double getHoursWorked() {
		return hoursWorked;
	}

	
	@Override 
	public double getDiscount(Invoice invoice) {
		return 0;
	}
	
	@Override
	public double getSubtotal() {		
		
		double total = partsCost + (hourlyLaborCost*hoursWorked);
		return total;
	}

	@Override
	public String toString() {
		return "Repair [partsCost=" + partsCost + ", hourlyLaborCost=" + hourlyLaborCost + ", hoursWorked="
				+ hoursWorked + "]";
	}

}
