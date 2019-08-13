package com.revature.finalexample;

public class FinalExample {
	// final makes it impossible to extend a class
	// final makes it impossible for subclasses to override a method
	// final makes it impossible to change the value of a variable
	
	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		C c = new C();
		System.out.println(a.b.name);
		a.b.name = "Richard";
		System.out.println(a.b.name);
		// a.b = new Bean();
		System.out.println(a.x);
		// a.x = 4;
		
		a.method();
		b.method();
		c.method();
	}
}

class Bean {
	public String name = "Bean";
}

class A {
	// On variables: final prevents REASSIGNMENT
	// for object references, that means the memory address cannot be changed
	// for primitive values, that means the value cannot be changed.
	final int x = 5;
	final Bean b = new Bean();
	void method() {
		System.out.println("This is a method. A");
	}
}

class B extends A {
	final void method() {
		System.out.println("This is a final method. B");
	}
}

final class C extends B {
//	final void method() {
//		
//	}
}

class D /* extends C */ {
	
}


