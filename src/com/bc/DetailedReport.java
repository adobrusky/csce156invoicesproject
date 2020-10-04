/**
 * Authors: Austin Dobrusky, Mark Forgét
 * Date:10/4/20
 * Description: prints the invoice details report
 */
package com.bc;

import java.util.Arrays;
import java.util.List;

public class DetailedReport extends ReportFormat {

	public static String print(List<Invoice> invoices) {
		
		String columnFormat = "  %-20s%-70s%-13s%-13s%-13s%-13s\n";
		
		//Top layer formatting
		String result = "";
		result += "Invoice Details:\n";
		result += multiplyString("=+", 55) + "\n";
		
		//Body formatting
		for(Invoice i : invoices) {
			
			//Owner and Customer info formatting
			result += "Invoice " + i.getInvoiceCode() + "\n";
			result += multiplyString("-", 50) + "\n";
			result += "Owner:\n" + String.format("\t%s, %s\n\t%s\n\t%s\n\t%s, %s %s %s\n", i.getOwner().getLastName(), i.getOwner().getFirstName(), 
					Arrays.toString(i.getOwner().getEmails()), i.getOwner().getAddress().getStreet(), i.getOwner().getAddress().getCity(), 
							i.getOwner().getAddress().getState(), i.getOwner().getAddress().getCountry(), i.getOwner().getAddress().getZip());
			result += "Customer:\n" + String.format("\t%s (%s Account)\n\t%s\n\t%s, %s %s %s\n", i.getCustomer().getName(), 
					typeToString(i.getCustomer().getType()), i.getCustomer().getAddress().getStreet(), i.getCustomer().getAddress().getCity(), 
					i.getCustomer().getAddress().getState(), i.getCustomer().getAddress().getCountry(), i.getCustomer().getAddress().getZip());
			result += "Products:\n" + String.format(columnFormat, "Code", "Description", " Subtotal", " Discount", " Taxes", " Total");
			result += "  " + multiplyString("-", 140) + "\n";
			
			//Product list formatting
			double subtotal = 0;
			double discount = 0;
			double taxes = 0;
			double total = 0;
			double grandTotal = 0;
			for(Product j : i.getListOfProducts()) {
				subtotal += j.getSubtotal();
				discount += j.getDiscount(i);
				taxes += j.getTaxes(i, i.getCustomer().getTaxRate());
				total += j.getTotal(i, i.getCustomer().getTaxRate());
				result += buildProductInfo(columnFormat, j, $(j.getSubtotal()), $(j.getDiscount(i)), 
						$(j.getTaxes(i, i.getCustomer().getTaxRate())), $(j.getTotal(i, i.getCustomer().getTaxRate()))) + "\n";
			}
			
			//Bottom totals formatting
			result += multiplyString("=", 142) + "\n";
			result += String.format("%-90s  %-13s%-13s%-13s%-13s\n", "ITEM TOTALS:", $(subtotal), $(discount), $(taxes), $(total));
			
			grandTotal = total;
			if(i.getCustomer().getType() == 'B') {
				result += String.format("%-129s  %-13s\n", "BUSINESS ACCOUNT FEE:", $(75.50));
				grandTotal += 75.50;
			} else if(i.getCustomer().getType() == 'P' && i.getCustomer().getPrimaryContact().getEmails().length >= 2) {
				result += String.format("%-129s  %-13s\n", "LOYAL CUSTOMER DISCOUNT (5% OFF):", $(i.getLoyaltyDiscount()));
				grandTotal += i.getLoyaltyDiscount();
			}
			
			result += String.format("%-129s  %-13s\n", "GRAND TOTAL:", $(grandTotal));
			result += "\n\n\tTHANK YOU FOR DOING BUSINESS WITH US!\n\n\n\n" + multiplyString("=+", 58) + "\n";
		}
		return result;
	}
}
