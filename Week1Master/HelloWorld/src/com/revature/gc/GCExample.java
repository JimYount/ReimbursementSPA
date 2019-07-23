package com.revature.gc;

public class GCExample {
	public static void main(String[] args) {
		Garbage g = null;
		for(int i = 0; i< 50; i++) {
			g = new Garbage(i+"");
			//System.gc();
		}
		//System.gc();
		System.out.println("Kept a reference to: "+g);
	}
}
