package com.revature.hello;

public class Method {
	public static void main(String[] args) {
		System.out.println("Hello World");
		main();
		System.out.println(method());
		method("tree");
		method(5);
		//method("tree", 5);
		method(5, "tree");
		(new Method()).instanceMethod();
	}
	
	public static void main() {
		System.out.println("This is not the main method");
	}
	
	public static int method() {
		System.out.println("int method() called.");
		return 5;
	}
	public static void method(int i) {
		System.out.println("void method(int) called.");
	}
	public static void method(String string) {
		System.out.println("void method(string) called.");
	}
	public static void method(int i , String string) {
		System.out.println("void method(int, string) called.");
	}
	public void instanceMethod() {
		System.out.println("Hello from a non-static method");
	}
}
