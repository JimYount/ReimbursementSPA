package com.revature.control;

import java.util.Scanner;

public class Control {
	public static void main(String[] args) {
		// When we talk about control in an application
		// we are usually referring to the entity that is 
		// currently executing on a thread.
		// The flow of control is the passing of control
		// through method calls and returns.
		
		//ifStatement();
		//loops();
		//enhancedFor();
		//ternary();
		//shortCircuit();
		//breakControl();
		switchStatements();
	}

	public static void switchStatements() {
		// In Java 7, Switch got a massive upgrade
		Scanner scan = new Scanner(System.in);
		System.out.println("Input a value: ");
		int x = scan.nextInt();
		
		// Switch statments can take in as an argument
		// convertible int types, Strings, and Enums
		switch(x) {
		case 0: System.out.println("x was 0"); break;
		case 1: System.out.println("x was 1"); break;
		case 5: 
			System.out.println("x was 5");
			System.out.println("So many lines");
			break;
		case 3: System.out.println("x was 3"); break;
		default: System.out.println("x unrecognized"); break;
		case 57: System.out.println("why so high?"); break;
		}
	}

	public static void breakControl() {
		// labels, breaks, and continues
//		for(int i = 0; i<10; i++) {
//			System.out.print(i+" ");
//			if(i == 5) {
//				System.out.println();
//				continue; // move to the next iteration of the loop
//			}
//			if(i == 7)
//				break; // terminate the loop
//			System.out.println(i);
//		}
		//goto bob;
		loop1: for(int i = 0; i<4; i++) {
			bob: for(int j= 0; j<4; j++) {
				if(i==1 && j>1) {
					continue loop1;
				}
				if(i==2 && j==0) {
					continue bob;
				}
				if(i==0 && j==1) {
					break;
				}
				System.out.println(i+" "+j);
			}
		}
	}

	public static void shortCircuit() {
		int x = 6;
		int y = 6;
		int z = 6;
		
		System.out.println("x = "+(++x)+", y="+(y++)+", z="+z);
		if(x++ == ++y && ++x == z++) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		System.out.println("x = "+x+", y="+y+", z="+z);
	}

	public static void ternary() {
		// An operator which returns values based on the
		// truth of a condition
		
		System.out.println(("hello" instanceof String) ? "yes" : "no");
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Input a value: ");
		int x = Integer.parseInt(scan.nextLine());
		
		// ternary form of the if statement below
		String s = x>3 ? "x is greater than 3" : "x is not greater than 3";
		System.out.println(s);
		
		// nested ternaries can be very difficult to read
		s = (x>3)?"x is greater than 3": (x<3)? "x is less than 3": "x is equal to 3";
		System.out.println(s);
	}

	public static void enhancedFor() {
		String[] animals = {"Horse", "Dog", "Bat", "Iguana"};
		for(int i = 0; i<animals.length; i++) {
			System.out.println(animals[i]);
		}
		System.out.println();
		for(String bob: animals) {
			System.out.println(bob);
		}
	}

	public static void loops() {
		// for
		for(int i = 0; i<10; i++) {
			System.out.println(i);
		}
		
		{
			int i = 0;
			while(i<10) {
				System.out.println(i);
				i++;
			}
		}
		System.out.println("fancy");
		{
			int i = 5;
			int j = 1;
			while(j<i) {
				System.out.println(j);
				j++;
				i--;
			}
		}
		for(int i =5, j =1; j<i; j++, i--) {
			System.out.println(j);
		}
//		for(;;) {
//			System.out.println("Infinity");
//		}
//		while(true) {
//			System.out.println("Also infinite");
//		}
		
		System.out.println("do while");
		int i = 150;
		do {
			System.out.println(i);
			i++;
		} while(i < 10);
		
		for(i = 0; i<5; i++) {
			for(int j =0; j<5; j++) {
				System.out.print(i+" "+j+"|");
			}
			System.out.println();
		}
	}

	public static void ifStatement() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Input a value: ");
		int x = Integer.parseInt(scan.nextLine());
		// System.out.println(x);
		// Simple if statement
		
//		if(x>3) {
//			System.out.println("x is greater than three");
//			System.out.println("something");
//		}
		
		// simple if-else
		
//		if(x>3) {
//			System.out.println("x is greater than three");
//		} else {
//			System.out.println("x is not greater than three");
//		}
		
		// slightly more complex if-else-if-else
		if(x>3) {
			System.out.println("x is greater than three");
		} else {
			if(x<3) {
				System.out.println("x is less than three");
			} else {
				System.out.println("x is equal to three");
			}
		}
		
		// valid but harder to maintain
		if(x>3)
			System.out.println("x is greater than three");
		else
			if(x<3)
				System.out.println("x is less than three");
			else
				System.out.println("x is equal to three");
	}
}
