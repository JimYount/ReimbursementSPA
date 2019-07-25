package com.revature.threads;

import java.util.ArrayList;

public class ThreadPlayground {
	public static void main(String[] args) {
		arrayList();
	}

	private static void arrayList() {
		ArrayList<Integer> arrList = new ArrayList<Integer>(100);
		Runnable r = () -> {
			for(int i = 50; i<100; i++) {
				arrList.add(i, i+1);
			}
		};
		Thread t = new Thread(r);
		t.start();
		for(int i = 0; i<50; i++) {
			arrList.add(i, i+1);
		}
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(arrList);
	}
}
