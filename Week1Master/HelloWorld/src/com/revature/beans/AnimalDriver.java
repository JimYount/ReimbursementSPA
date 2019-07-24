package com.revature.beans;

public class AnimalDriver {

	public static void main(String[] args) {
		//animal();
		//dog();
		//abstractionAndPolymorphism();
		breathing();
	}

	private static void breathing() {
		Breathable b = new Chihuahua();
		b.breathes();
		b.hold();
	}

	public static void abstractionAndPolymorphism() {
		Animal a = new Chihuahua();
		a.walk();
		((Chihuahua)a).quiver();
	}

	public static void dog() {
		Dog d = new Dog();
		Animal a = new Dog();
		a = d; // can implicitly cast to super-class
		d = (Dog) a; // cannot implicitly down-cast
		d.setName("Fido");
		d.speak();
		System.out.println(d.name);
		d.walk();
	}

	public static void animal() {
//		Animal a = new Animal();
//		a.speak();
//		a.setName("Richard");
//		a.speak();
//		System.out.println(a);
//		a.name = "Milton";
//		a.speak();
	}

}
