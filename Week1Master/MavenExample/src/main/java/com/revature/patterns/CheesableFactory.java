package com.revature.patterns;

public class CheesableFactory {
	// A factory will provide users with an instance of an interface
	public Cheesable getCheesable() {
		return new Croissant();
	}

}
