package com.revature.generics;

import java.util.function.Predicate;

import org.apache.log4j.Logger;

public class LambdaPredicate {
	private static Logger log = Logger.getLogger(LambdaPredicate.class);
	/*
	 * A Lambda Function:
	 * 	A piece of code that is written to perform a single task once.
	 * 		Classes are good reusable code.
	 * 		Class: A Reusable Code Module
	 * 		Lambda: A single-use code module
	 * 	The Reason we have lambdas is write code for that single task.
	 * 		No namespace clutter, no codebase expansion
	 * 
	 * Function Interface: An interface with a single abstract method.
	 * 		When you create a lambda, you are instantiating that interface
	 * Predicate: A functional interface for testing methods.
	 * 		It has a single method: test();
	 * 		It has a single parameter: The object being tested.
	 */
	public static void main(String[] args) {
		Calculator<Integer> intCalc = new Calculator<Integer>();
		log.trace(intCalc.add(1, 32));
		Calculator<Double> doubCalc = new Calculator<Double>();
		log.trace(doubCalc.sub(2., 1., 0., 4.));
		
		Predicate<Calculator<Long>> p = (calc) -> calc.add(4l, 5l) == 9;
		Calculator<Long> c = new Calculator<Long>();
		log.debug(p.test(c));
		
		p = (bob) -> {
			Long l = bob.div(5l,  0l);
			return Long.MAX_VALUE == l;
		};
		log.debug(p.test(c));
		
		
		log.trace(-5.0/0.0);
	}
	
	
}
