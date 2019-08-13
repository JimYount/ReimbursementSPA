package com.revature.scopes;

public class ScopeExample {
	/*
	 * Scope
	 * 	The lifetime of a reference
	 * Java has 4 scopes
	 * 
	 * Class/Static scope - Variables that belong to the class itself.
	 * 		Variables inside of this scope are instantiated when the class
	 * 		is loaded. They are available (they exist) until the program
	 * 		terminates or the class somehow gets unloaded.
	 * Object/Instances scope - An object's fields/state.
	 * 		Variables in this scope exist for the lifetime of an object.
	 * Method scope - The parameters and any variables defined within a method.
	 * Local/Loop/Block scope - any variable defined within curly braces or some kind
	 * 		of control block. Variables in this scope exist until the control flow
	 * 		exits the loop or block.
	 */
	
	static {
		// static block. A code block that executes when a class is loaded.
		// A good way to set up a class.
		System.out.println("Static Block.");
		i = 4;
		// j = 4;
		ScopeExample s = new ScopeExample();
		s.j = 3;
		//k = 2;
	}
	{
		// instance block. A code block that executes when an 
		//	instance of an object is created.
		System.out.println("Instance Block");
	}
	
	private static int i;
	private int j;
	
	public ScopeExample() {
		System.out.println("Constructor call");
		i = 5;
		j = 22;
	}
	
	public static void main(String[] args) {
		System.out.println("Main Method.");
		i = 4;
		ScopeExample s = new ScopeExample();
		s.j=4;
		int k = 3;
	}
}
