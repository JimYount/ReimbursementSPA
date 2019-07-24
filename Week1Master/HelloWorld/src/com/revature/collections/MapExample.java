package com.revature.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class MapExample {
	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "Hans");
		System.out.println(map);
		map.put(1, "Luis");
		System.out.println(map);
		map.put(2, "Hans");
		System.out.println(map);
		map.put(3, "Hans");
		System.out.println(map);
		map.put(4, "Andrew");
		System.out.println(map);
		map.put(-11, "Alex");
		System.out.println(map);
		map.put(999999, "Kerwin");
		System.out.println(map);
		
		
		Map<Integer, String> treeMap = new TreeMap<Integer, String>();
		treeMap.putAll(map);
		System.out.println(treeMap);
		
		System.out.println(map.get(4));
		System.out.println(map.get(-4));
		
		System.out.println(map.remove(-4));
		System.out.println(map.remove(4));
		System.out.println(map);
		
		// iterate over the keys of the map
		for(Integer i : map.keySet()) {
			System.out.print(i+" "+map.get(i)+", ");
		}
		System.out.println();
		// Iterate over the entries in the map
		for(Entry<Integer, String> e : map.entrySet()) {
			System.out.println(e);
		}
		
		// Iterate over the values directly
		for(String s : map.values()) {
			System.out.println(s);
		}
		
		System.out.println(map.getClass().getName());
		System.out.println(map.values().getClass().getName());
		System.out.println("Is values a collection? "+(map.values() instanceof Collection));
		System.out.println("Is values a set? "+(map.values() instanceof Set));
		System.out.println("Is values a list? "+(map.values() instanceof List));
		System.out.println("Is values a queue? "+(map.values() instanceof Queue));
	}
}
