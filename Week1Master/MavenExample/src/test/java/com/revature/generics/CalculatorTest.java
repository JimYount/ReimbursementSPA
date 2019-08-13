package com.revature.generics;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// JUnit is a unit test framework for Java.	
public class CalculatorTest {
	private static Logger log = Logger.getLogger(CalculatorTest.class);
	private static Calculator<Double> doubCalc;
	@BeforeClass
	public static void setupClass() {
		log.trace("Set up something for the entire run.");
	}
	@AfterClass
	public static void tearDownClass() {
		log.trace("tear that thing back down.");
	}
	@Before // A method that will run before every test
	public void setup() {
		log.trace("Creating a new calculator");
		doubCalc = new Calculator<Double>();
	}
	@After // A method that will run after every test
	public void tearDown() {
		log.trace("Tearing down resources");
		doubCalc = null;
	}
	@Test
	public void multiplicationOfZeroReturnsZero() {
		log.trace("Testing multiplication of Zero equals zero");
		assertEquals("Test if 5*0 = 0", new Double(0.0),
				doubCalc.mult(5.0, 0.0));
		assertEquals("Test if 8*0 = 0", new Double(0.0),
				doubCalc.mult(8.0, 0.0));
		assertEquals("Test if 9*0 = 0", new Double(0.0),
				doubCalc.mult(8.0, 0.0));
	}
	@Test
	public void divisionOfZeroReturnsInfinity() {
		log.trace("Testing division of Zero equals infinity");
		assertEquals("Test if 5/0 = infinity", new Double(Double.POSITIVE_INFINITY),
				doubCalc.div(5.0, 0.0));
		assertEquals("Test if 8/0 = infinity", new Double(Double.POSITIVE_INFINITY),
				doubCalc.div(8.0, 0.0));
		assertEquals("Test if 9/0 = infinity", new Double(Double.POSITIVE_INFINITY),
				doubCalc.div(9.0, 0.0));
	}
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowAnException() {
		log.warn("About to call a method that shouldn't exist");
		doubCalc.test();
	}
}
