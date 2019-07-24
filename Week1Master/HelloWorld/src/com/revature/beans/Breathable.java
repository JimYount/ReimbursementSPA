package com.revature.beans;

public interface Breathable {
	// Interfaces cannot have non-public members
	//			Except in Java 9
	// Interface methods are implicitly abstract
	// abstract method: a method with no implementation
	
	/*public abstract*/ void breathes();
	
	// As of Java 8: Interfaces can have member variables.
	// Interface variables are implicitly public, static, and final
	String AIR = "Oxygen";
	
	// As of Java 8: Interfaces can have default implementation
	default void hold() {
		System.out.println("Holding breath.");
	}
}
