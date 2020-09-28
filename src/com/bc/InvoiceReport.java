package com.bc;

//InvoiceReport class will print the summary report and the invoice details report based on the parsed lists from the .dat files
public class InvoiceReport extends DataConverter {
	
	public static String multiplyString(String in, int rep) {
		//Multiply strings. Makes it easier for formatting the reports with repeated characters
		String result = "";
		for(int i = 0; i < rep; i++) {
			result += in;
		}
		return result;
	}
	
	public static String $(double value) {
		//Formats the inputed value into the currency structure used in the reports
		return String.format("$%10.2f", value);
	}
	
	public static String typeToString(char type) {
		//Returns type of customer as a string value instead of char
		if(type == 'P') {
			return "Personal";
		} else {
			return "Business";
		}
	}
	
	public static String buildProductInfo(String columnFormat, Product product, String subtotal, String discount, String string, String total) {
		//Builds the code, description, subtotal, discount, taxes, and total column formats for the invoice details report
		String result = "";
		result += String.format(columnFormat.substring(0, 7), product.getCode());
		switch(product.getType()) {
			case 'R':
				result += String.format(columnFormat.substring(7), product.getLabel() + 
						" (" + ((Rental)product).getDaysRented() + " days @ $" + ((Rental)product).getDailyCost() + "/day)", 
						subtotal, discount, string, total);
				result += String.format(columnFormat.substring(0, 12), "", "(+ $" + ((Rental)product).getCleaningFee() + " cleaning fee, -$" + ((Rental)product).getDeposit() + " deposit refund)");
				break;
			case 'F':
				result += String.format(columnFormat.substring(7), product.getLabel() + 
						" (" + ((Repair)product).getHoursWorked() + " hours of labor @ $" + ((Repair)product).getHourlyLaborCost() + "/hour)", 
						subtotal, discount, string, total);
				result += String.format(columnFormat.substring(0, 12), "", "(+ $" + ((Repair)product).getPartsCost() + " for parts)");
				break;
			case 'T':
				result += String.format(columnFormat.substring(7, 32), product.getLabel() + 
						" (" + ((Towing)product).getMilesTowed() + " miles @ $" + ((Towing)product).getCostPerMile() + "/mile)", 
						subtotal, discount, string, total);
				break;
			case 'C':
				result += String.format(columnFormat.substring(7, 32), product.getLabel() + 
						" (" + ((Concession)product).getQuanity() + " units @ $" + ((Concession)product).getUnitCost() + "/unit)", 
						subtotal, discount, string, total);
				break;
		}

		return result;
	}

	public static void main(String[] args) {
		
		//Print the reports
		System.out.println(SummaryReport.print(getInvoices()) + "\n\n");
		System.out.println(DetailedReport.print(getInvoices()));
		
	}

}
