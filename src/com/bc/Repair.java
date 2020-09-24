package com.bc;

public class Repair extends Product {
	
	private double partsCost;
	private double hourlyLaborCost;
	private int hoursWorked;

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

	public int getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	@Override
	public String toString() {
		return "Repair [partsCost=" + partsCost + ", hourlyLaborCost=" + hourlyLaborCost + ", hoursWorked="
				+ hoursWorked + "]";
	}

}
