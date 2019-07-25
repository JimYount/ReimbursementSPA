package com.revature.threads;

public class ThreadExample {
	public static void main(String[] args) {
		System.out.println("This is the main thread.");
		//thread();
		//runnable();
		lambda();
		System.out.println("Main method is over.");
	}

	private static void lambda() {
		Runnable run = () -> {
			System.err.println("I'm a thread");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.err.println("The thread is ending.");
		};
		Thread myThread = new Thread(run);
		myThread.start(); // Start actually creates a new thread
		// Start will call the run() method for that thread.
		System.out.println("Started a new thread!");
		
		System.out.println("Is the thread alive? "+myThread.isAlive());
		System.out.println(myThread.getState());
		try {
			myThread.join(); // sleep until the other thread is done.
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(myThread.getState());
		System.out.println("Is the thread alive? "+myThread.isAlive());
		System.out.println("Returning control to main method.");
	}

	private static void runnable() {
		Thread myThread = new Thread(new MyRunnable());
		myThread.start(); // Start actually creates a new thread
		// Start will call the run() method for that thread.
		System.out.println("Started a new thread!");
		
		System.out.println("Is the thread alive? "+myThread.isAlive());
		System.out.println(myThread.getState());
		try {
			myThread.join(); // sleep until the other thread is done.
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(myThread.getState());
		System.out.println("Is the thread alive? "+myThread.isAlive());
		System.out.println("Returning control to main method.");
	}

	private static void thread() {
		Thread myThread = new MyThread();
		myThread.start(); // Start actually creates a new thread
		// Start will call the run() method for that thread.
		System.out.println("Started a new thread!");
		
		System.out.println("Is the thread alive? "+myThread.isAlive());
		System.out.println(myThread.getState());
		try {
			myThread.join(); // sleep until the other thread is done.
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(myThread.getState());
		System.out.println("Is the thread alive? "+myThread.isAlive());
		System.out.println("Returning control to main method.");
	}
}
class MyRunnable implements Runnable {
	@Override
	public void run() {
		System.err.println("I'm a thread");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("The thread is ending.");
	}
}

class MyThread extends Thread {
	// Extending thread exposes the start() method to overriding
	// if we override the start() method incorrectly, the thread
	// will not be processing in parallel.
	@Override
	public void run() {
		System.err.println("I'm a thread");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("The thread is ending.");
	}
}
