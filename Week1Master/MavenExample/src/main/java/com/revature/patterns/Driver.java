package com.revature.patterns;

public class Driver {
	public static void main(String[] args) {
		//singleton();
		factory();
	}

	public static void factory() {
		CheesableFactory cf = new CheesableFactory();
		cf.getCheesable().spreadCheese("Brie");
	}

	public static void singleton() {
		//SingletonExample s = new SingletonExample();
		SingletonExample s = SingletonExample.getSingletonExample();
		SingletonExample s1 = SingletonExample.getSingletonExample();
		System.out.println(s == s1);
		System.out.println(s.getMessage());
		System.out.println(s1.getMessage());
		s.setMessage("tree");
		System.out.println(s.getMessage());
		System.out.println(s1.getMessage());
	}
}

//class SingletonChild extends SingletonExample{
//	public SingletonChild(){
//		super();
//	}
//}
