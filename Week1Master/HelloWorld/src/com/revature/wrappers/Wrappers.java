package com.revature.wrappers;

import java.util.Arrays;

public class Wrappers {

	// primitives
	// int - 4 bytes - Integer
	// double - 8 bytes - Double
	// float - 4 bytes - Float
	// long - 8 bytes - Long
	// short - 2 bytes - Short
	// char - 2 bytes - Character
	// byte - 1 bytes - Byte
	// boolean - JVM Dependent - Boolean
	public static void main(String[] args) {
		System.out.println(Double.BYTES);
		System.out.println(Short.MAX_VALUE);

		// literals();
		// primitiveSizes();
		// convertibleIntValues();
		// comparingPrimitivesAndWrappers();
		// System.out.println(customParsing("-546"));
		//System.out.println(Integer.parseInt("-546"));
		overloading();
	}
	// Overloading preference:
	// 1. Exact Match = Is there a method that matches the parameter list exactly?
	// 2. Implicit Cast = Is there a way to cast the parameter implicitly to match?
	// 3. AutoBoxing = Is there a wrapper/primitive we can wrap/unwrap to?
	// 4. Varargs = Can I convert the last parameters to an array?
	private static void overloading() {
		int intPrimitive = 5;
		Integer intObject = 5;
		short shortPrimitive = 5;
		Short shortObject = 5;
		long longPrimitive = 5;
		Long longObject = 5l;
		byte b = 5;
		char c = '3';
		
		method(intPrimitive);
		method(intObject);
		method(shortPrimitive);
		method(shortObject);
		method(longPrimitive);
		method(b);
		method(c);
		
		method(intPrimitive, longPrimitive);
		// method(intPrimitive, longObject);
		method(intPrimitive, intPrimitive);
		
		method(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		int[] x = {1,1,1,1,1,1,1};
		method(1, x);
	}

	// Object Integer, primitive long
	public static void method(Integer x, long y) {
		System.out.println("object Integer, primitive long passed: " + x + ", " + y);
	}
	public static void method(int x, long y) {
		System.out.println("primitive integer, primitive long passed: " + x + ", " + y);
	}
	public static void method(int x) {
		System.out.println("primitive int passed: " + x);
	}
	public static void method(short s) {
		System.out.println("primitive short passed: " + s);
	}
	public static void method(Short s) {
		System.out.println("object short passed: " + s);
	}
	public static void method(Long l) {
		System.out.println("object long passed: " + l);
	}

	// Vararg - variable arguments
	// Varargs must be the last parameter of a method.
	// There can only be one varargs parameter
	// You can pass an array into varargs.
	public static void method(int i, int... x) {
		System.out.println("int, varargs int: "+i+", "+ Arrays.toString(x));
	}
	public static int customParsing(String string) {
		int result = 0;
		char c[] = string.toCharArray();
		int i = 0;
		boolean neg = false;

		while (i < c.length) {
			int j = 0;
			if ('-' == c[i]) {
				neg = true;
			} else {
				j = (int) (c[i] - 48);
			}
			result += j * (int) Math.pow(10, c.length - (i + 1));
			i++;
		}
		return neg ? -1 * result : result;
	}

	private static void comparingPrimitivesAndWrappers() {
		// == operator: compares the values of it's operands
		Object obj = new Object(); // the value of obj
		// is the memory address of the object
		Object obj2 = new Object();
		System.out.println(obj == obj2);
		// Object class has the .equals() method
		// Every object inherits .equals() and should
		// override to provide a valid true/false comparison
		// In the base object class, .equals compares hashCodes? maybe?
		System.out.println(obj.equals(obj2));

		int intPrimitive = 5;
		Integer intObject = 5;
		Integer newObject = new Integer(5);

		short shortPrimitive = 5;
		Short shortObject = 5;

		long longPrimitive = 5;
		Long longObject = 5l;

		// 5 is an integer. 5.0 is a double. 5f or 5.0f is a float
		float floatPrimitive = 5.0f;
		Float floatObject = 5f;

		double doublePrimitive = 5;
		Double doubleObject = 5.0;

		// Auto-unboxing - The JVM will automatically "unwrap" an object to a primitive
		System.out.println("intPrimitive==intObject: " + (intPrimitive == intObject));
		System.out.println("intPrimitive==newObject: " + (intPrimitive == newObject));
		System.out.println("intObject==newObject: " + (intObject == newObject));
		System.out.println("intObject.equals(newObject): " + (intObject.equals(newObject)));
		System.out.println("intPrimitive == longPrimitive: " + (intPrimitive == longPrimitive));
		System.out.println("intObject.equals(longObject): " + intObject.equals(longObject));
		System.out.println("intObject.equals(longObject.intValue()): " + intObject.equals(longObject.intValue()));
	}

	private static void convertibleIntValues() {
		int i;
		boolean bool = true;
		// i= bool;
		byte b = 8;
		i = b;
		char c = '8';
		i = c;
		short s = 8;
		i = s;
		float f = 8;
		i = (int) f; // if I know that it will convert, I can force it.
		long l = 8;
		i = (int) l;
		double d = 8;
		i = (int) d;
		Long longExample = (long) Integer.MAX_VALUE + 10;
		System.out.println(longExample);
		i = (int) longExample.longValue();
		System.out.println(i);
		f = 5999999999999999999999999999999999999f; // 5.6
		System.out.println(f);
		i = (int) f;
		System.out.println(i);
	}

	private static void primitiveSizes() {
		// byte -2^7-2^7-1
		System.out.println(Byte.MIN_VALUE + " - " + Byte.MAX_VALUE);
		// character? 0-2^16-1
		System.out.println((int) Character.MIN_VALUE + " - " + (int) Character.MAX_VALUE);
		// short? -2^15 - 2^15-1
		System.out.println(Short.MIN_VALUE + " - " + Short.MAX_VALUE);
		System.out.println("Float: " + Float.MIN_VALUE + " - " + Float.MAX_VALUE);

		System.out.println("Integer: " + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE);
		System.out.println("Long: " + Long.MIN_VALUE + " - " + Long.MAX_VALUE);
		System.out.println("Double: " + Double.MIN_VALUE + " - " + Double.MAX_VALUE);
	}

	public static void literals() {
		long l = 56; // 56 is an int literal
		Long lObject = l; // the long literal type is implicitly
		// wrapped within a Long object.
		// Long lObject = new Long(l);
		System.out.println(lObject.longValue());
		// lObject = 34; // an int literal and I'm trying to wrap
		// an int into a Long
		lObject = 34l;

		double d = 0;
		float f = 0;
		f = 65;
		f = 65.0f; // 65.0 is a double literal, we need the f for floats
		char c = 65;
	}

}
