package com.revature.hello;

public class HelloWorld {
	public String variableNames;
	
	public void methodNames() {
		
	}
	
	public static final String UPPER_CASE = "I am a constant";
	
	//public const String hello;
	
	// line comments
	/* 
	 * block comments
	 * sometimes referred to as multiline comments
	 */
	
	private String message;
	private static String staticMessage;
	
	public static void main (String[] args) {
		System.out.println("Hello World");
		//return;
		/*
		 * I can't access a non-static object from a static context
		 * Anything that is not static belongs to an object made from the class
		 * Anything that is static belongs to the class itself
		 * The message object literally does not exist unless
		 * 		we make a HelloWorld object
		 */
		//message = "Hello";
		staticMessage = "Hi, I'm static";
		System.out.println(HelloWorld.staticMessage);
		HelloWorld h = new HelloWorld();
		HelloWorld h2 = new HelloWorld();
		h.message = "tree";
		System.out.println(h.message);
		System.out.println(h2.message);
		//System.out.println(h2.staticMessage);
		//h2.staticMessage = "Goodbye";
		//System.out.println(h.staticMessage);
	}
	
	/*
	 * If you provide NO OTHER constructor for a class, Java will
	 * provide the default no-arguments constructor.
	 */
	
	// This is what the default constructor looks like.
//	public HelloWorld() {
//		super();
//	}
	
	public HelloWorld(String message) {
		//super();
		this.message = message;
	}
	public HelloWorld() {
		this("Tree");
	}
}
