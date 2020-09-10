package com.bc;

public abstract class Product {
	private String code;
	private char type;
	private String label;
	
	public Product(String c, char t, String l) {
		setCode(c);
		setType(t);
		setLabel(l);
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public char getType() {
		return type;
	}
	
	public void setType(char type) {
		this.type = type;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", type=" + type + ", label=" + label + "]";
	}
	
	
}
