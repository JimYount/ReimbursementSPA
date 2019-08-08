package com.revature.beans;

public class DataObject {
	private String[] values;
	private String[][] result;
	
	public DataObject(int x, int y) {
		result = new String[x][y];
	}
	
	public DataObject(String[] args) {
		values = new String[args.length];
		
		for (int i = 0; i < args.length; i++) {
			values[i] = args[i];
		}
	}
	
	public void setResult(int x, int y, String input) {
		result[x][y] = input;
	}
	
	public String[] getValues() {
		return values;
	}
	
	public String[][] getResult () {
		return result;
	}
}
