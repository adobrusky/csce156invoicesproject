package com.bc;

//Rental class holds all of the information regarding a rental product
public class Rental extends Product {
	
	private double dailyCost;
	private double deposit;
	private double cleaningFee;
	private double daysRented; 
	
	public Rental(String code, char type, String label, double dailyCost, double deposit, double cleaningFee) {
		super(code, type, label);
		this.dailyCost = dailyCost;
		this.deposit = deposit;
		this.cleaningFee = cleaningFee;
	}
	
	//Copy constructor
	public Rental(Rental old, double d) {
		super(old.getCode(), old.getType(), old.getLabel());
		this.dailyCost = old.getDailyCost();
		this.deposit = old.getDeposit();
		this.cleaningFee = old.getCleaningFee();
		this.daysRented = d;
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

	public double getDaysRented() {
		return daysRented;
	}


	@Override
	public double getSubtotal() {
		
		double total = (daysRented*dailyCost) + cleaningFee - deposit;
		return total;
		
	}
	
	@Override 
	public double getDiscount(Invoice invoice) {
		return 0;
	}
	
	@Override
	public String toString() {
		return "Rental [dailyCost=" + dailyCost + ", deposit=" + deposit + ", cleaningFee=" + cleaningFee
				+ ", daysRented=" + daysRented + "]";
	}

}
