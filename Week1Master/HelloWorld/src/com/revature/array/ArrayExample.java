package com.revature.array;

import java.util.Arrays;

public class ArrayExample {
	public static void main(String[] args) {
		int[] arr = new int[7];
		int[] arr1 = {1, 2, 3, 4};
		Integer[] arr2 = new Integer[5];
		
		System.out.println(arr1[2]);
		System.out.println();
		for(int i = 0; i< arr1.length; i++) {
			System.out.println(arr1[i]);
		}
		System.out.println(arr1);
		System.out.println(Arrays.toString(arr1));
		
		System.out.println(Arrays.toString(arr));
		Arrays.fill(arr,  7);
		System.out.println(Arrays.toString(arr));
		
		int[] arr3 = {5,6,3,4,2,6,7,3,45,6,2,3};
		System.out.println(Arrays.toString(arr3));
		Arrays.sort(arr3);
		System.out.println(Arrays.toString(arr3));
	}
}
