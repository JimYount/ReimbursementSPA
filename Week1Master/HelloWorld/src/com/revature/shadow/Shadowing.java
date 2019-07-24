package com.revature.shadow;

public class Shadowing {
	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		System.out.println(((A)b).x + " "+((A)b).method()); //what does this print?
	}
}
class A {
	public int x = 42;
	public int method() {
		return x;
	}
}
class B extends A {
	public int x = 24;
	@Override
	public int method() {
		return x;
	}
}
