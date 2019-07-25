package com.revature.io;

import java.util.Scanner;

public class ScannerExample {
	public static void main(String[] args) {
		//closingScanner();
		scannerNextAnythingButLine();
	}

	private static void scannerNextAnythingButLine() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your age: ");
		//int i = scan.nextInt();
		//scan.nextLine();
		int i = Integer.parseInt(scan.nextLine());
		System.out.println("Age is "+i);
		
		System.out.println("Enter your name: ");
		String s = scan.nextLine();
		System.out.println("Name is "+s);
		System.out.println("Hello "+s+". Thank you for telling us your age is "+i);
		
	}

	private static void closingScanner() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter a thing: ");
		System.out.println(scan.nextLine());
		
		scan.close();
		System.out.println("Enter a thing: ");
		scan = new Scanner(System.in);
		System.out.println(scan.nextLine());
		System.out.println("Hello?");
	}
}
