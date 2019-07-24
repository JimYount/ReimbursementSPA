package com.revature.collections;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetExample {
	public static void main(String[] args) {
		Set<Integer> hashSet = new HashSet<Integer>();
		System.out.println(hashSet);
		hashSet.add(5);
		hashSet.add(6);
		System.out.println(hashSet);
		System.out.println(hashSet.add(4));
		System.out.println(hashSet);
		System.out.println(hashSet.add(4));
		System.out.println(hashSet);
		hashSet.add(-11);
		System.out.println(hashSet);
		hashSet.add(11);
		System.out.println(hashSet);
		hashSet.add(9999999);
		System.out.println(hashSet);
		
		Set<Integer> treeSet = new TreeSet<Integer>();
		treeSet.addAll(hashSet);
		System.out.println(treeSet);
	}
}
