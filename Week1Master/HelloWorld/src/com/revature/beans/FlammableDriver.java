package com.revature.beans;

public class FlammableDriver {
	public static void main(String[] args) {
		Dog d = new Dog();
		Paper p = new Paper();
		//burn(d);
		burn(p);
		burn(d);
	}
	
	public static void burn (Flammable f) {
		System.out.println("Setting "+f+" on fire.");
	}
	public static void burn(Object o) {
		if (o instanceof Flammable) {
			System.out.println("Setting "+o+" on fire.");
		} else {
			throw new RuntimeException("Not Flammable");
		}
	}
}

class Paper implements Flammable {
	
}