package com.revature.beans;

public class Chihuahua extends Dog {
	public Chihuahua() {
		super(false);
	}
	@Override
	public void setAdorable(Boolean b) {
		throw new RuntimeException("Not adorable");
	}
	public void quiver() {
		System.out.println("A Lot.");
	}
}
