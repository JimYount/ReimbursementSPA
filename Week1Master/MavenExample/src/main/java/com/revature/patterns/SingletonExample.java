package com.revature.patterns;

public class SingletonExample {
	// Step 1: Private Constructor
	private SingletonExample() {
		super();
	}
	
	// Step 2: A private static instance of the object
	private static SingletonExample instance = null;
	
	// Step 3: A public static synchronized accessor method
	public static synchronized SingletonExample getSingletonExample() {
		if(instance == null)
			instance = new SingletonExample();
		return instance;
	}
	
	private String message = "Hello";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
