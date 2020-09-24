package com.bc;

public class Rental extends Product {
	
	private double dailyCost;
	private double deposit;
	private double cleaningFee;
	private int daysRented; 
	
	public Rental(String c, char t, String l, double d, double de, double cf) {
		super(c, t, l);
		setDailyCost(d);
		setDeposit(de);
		setCleaningFee(cf);
	}

	public double getDailyCost() {
		return dailyCost;
	}

	public void setDailyCost(double dailyCost) {
		this.dailyCost = dailyCost;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getCleaningFee() {
		return cleaningFee;
	}

	public void setCleaningFee(double cleaningFee) {
		this.cleaningFee = cleaningFee;
	}
	

	public int getDaysRented() {
		return daysRented;
	}

	public void setDaysRented(int daysRented) {
		this.daysRented = daysRented;
	}

	@Override
	public String toString() {
		return "Rental [dailyCost=" + dailyCost + ", deposit=" + deposit + ", cleaningFee=" + cleaningFee
				+ ", daysRented=" + daysRented + "]";
	}
	

}
