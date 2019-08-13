package com.revature.beans;

public class Dog extends Animal {
	//public String name = "d";
	
	public Dog() {
		super();
		super.setAdorable(true);
	}
	protected Dog(boolean adorable) {
		super();
		super.setAdorable(adorable);
	}
	
	@Override
	public void speak() {
		for(char c : this.getName().toCharArray()) {
			System.out.print("Bark");
		}
		System.out.println();
	}
	@Override
	public void walk() {
		System.out.println("Going for a walk.");
	}
	@Override
	public void breathes() {
		System.out.println("pant");
	}
	
//	public void setName(String name) {
//		this.name = name;
//	}
	
}
