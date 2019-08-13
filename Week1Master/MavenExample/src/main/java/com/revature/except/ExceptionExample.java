package com.revature.except;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class ExceptionExample {
	public static void main(String[] args) {
		//checkedUnchecked();
		//tryWithResources();
		compilationError();
		try {
			method1();
			System.err.println("No exception");
		} catch (TooMuchPieException e) {
			e.printStackTrace();
			//method2();
		} finally {
			System.err.println("Always");
		}
		System.err.println("Did we make it?");
	}
	private static void compilationError() {
		try{
			compError();
		} catch(Throwable t) {
			System.out.println("Caught compilation errror");
		}
	}
	public static void compError() {
		//a;lsdkjf;aslkjdf;alskjdf;askjdf;akjs;dfkj
	}

	public static void method1() throws TooMuchPieException {
		System.err.println("Eating pie in method 1");
		Random rand = new Random();
		int i= rand.nextInt(2);
		if(i==0) 
			throw new TooMuchPieException();
	}
	public static void method2() {
		System.err.println("Eating pie in method 2");
		throw new RuntimeException();
	}
	
	public static void tryWithResources() {
		try (FileReader f = new FileReader("C:\\Users\\rlayn\\GitRepos\\1907-jul22-java\\Week1Master\\MavenExample\\src\\main\\java\\com\\revature\\except\\ExceptionExample.java");){
			while(f.ready()) {
				System.out.print((char) f.read());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// The filereader (which implements AutoCloseable) is automatically
		// closed by the try(with resources)
	}

	public static void checkedUnchecked() {
		//int i = 5/0;
		//String s = null;
		//System.out.println(s.substring(0,1));
		FileReader f = null;
		try {
			f = new FileReader("C:\\Users\\rlayn\\GitRepos\\1907-jul22-java\\Week1Master\\MavenExample\\src\\main\\java\\com\\revature\\except\\ExceptionExample.java");
			while(f.ready()) {
				System.out.print((char) f.read());
			}
		} catch (Error e) {
			// anywhere above throwable
		} catch (FileNotFoundException | ArithmeticException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// literally any exception
		} catch (Throwable t) {
			// literally anything?
		} finally {
			// Finally always executes regardless of what happens above.
			// finally will only not run if the app terminates
			// This makes finally good for closing resources
			try {
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}

class TooMuchPieException extends Exception {
	public TooMuchPieException() {
		super("Oh no. I ate too much pie.");
	}
}
