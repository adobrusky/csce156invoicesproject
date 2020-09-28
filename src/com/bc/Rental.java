package com.bc;

//Rental class holds all of the information regarding a rental product
public class Rental extends Product {
	
	private double dailyCost;
	private double deposit;
	private double cleaningFee;
	private int daysRented; 
	
	public Rental(String code, char type, String label, double dailyCost, double deposit, double cleaningFee) {
		super(code, type, label);
		this.dailyCost = dailyCost;
		this.deposit = deposit;
		this.cleaningFee = cleaningFee;
	}

	public double getDailyCost() {
		return dailyCost;
	}

	public double getDeposit() {
		return deposit;
	}

	public double getCleaningFee() {
		return cleaningFee;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public void setDaysRented(int daysRented) {
		this.daysRented = daysRented;
	}

	@Override
	public double getSubtotal() {
		
		double total = (daysRented*dailyCost) + cleaningFee - deposit;
		return total;
		
	}
	
	@Override
	public String toString() {
		return "Rental [dailyCost=" + dailyCost + ", deposit=" + deposit + ", cleaningFee=" + cleaningFee
				+ ", daysRented=" + daysRented + "]";
	}
	

}
