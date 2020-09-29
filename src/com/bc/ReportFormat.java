package com.bc;

//ReportHelper is used for providing helper formatting functions in the summary and detailed invoice reports
public class ReportFormat {
	
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
}
