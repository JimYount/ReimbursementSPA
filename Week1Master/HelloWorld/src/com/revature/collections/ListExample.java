package com.revature.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

public class ListExample {
	public static void main(String[] args) {
		//arrayListExample();
		linkedListExample();
	}

	private static void linkedListExample() {
		LinkedList<Integer> link = new LinkedList<Integer>(); // EVERYTHING
		List<Integer> list = link;
		Deque<Integer> deque = link;
		Queue<Integer> queue = link;
		Collection<Integer> coll = link;
		Iterable<Integer> iterable = link;
		
		// QUEUE
		System.out.println("Queue Example");
		System.out.println("Queue: "+queue);
		queue.add(7);
		System.out.println("Queue: "+queue);
		queue.add(8);
		System.out.println("Queue: "+queue);
		queue.add(5);
		System.out.println("Queue: "+queue);
		
		System.out.println("Element: "+queue.element());
		System.out.println("Peek: "+queue.peek());
		System.out.println("Queue: "+queue);
		System.out.println("Remove: "+queue.remove());
		System.out.println("Queue: "+queue);
		
		System.out.println("Element: "+queue.element());
		System.out.println("Peek: "+queue.peek());
		System.out.println("Queue: "+queue);
		System.out.println("Remove: "+queue.remove());
		System.out.println("Queue: "+queue);
		
		System.out.println("Element: "+queue.element());
		System.out.println("Peek: "+queue.peek());
		System.out.println("Queue: "+queue);
		System.out.println("Remove: "+queue.remove());
		System.out.println("Queue: "+queue);
		
		// Element will throw an exception when nothing is in the queue.
		//System.out.println("Element: "+queue.element());
		// Peek will return null if nothing is in the queue.
		System.out.println("Peek: "+queue.peek());
		System.out.println("Queue: "+queue);
		// Remove will throw an exception when nothing is in the queue.
		// System.out.println("Remove: "+queue.remove());
		// Poll will return null if nothing is in the queue.
		System.out.println("Poll: "+queue.poll());
		System.out.println("Queue: "+queue);
	}

	public static void arrayListExample() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(5);
		list.add(1);
		list.add(11);
		list.add(6);
		System.out.println(list);
		list.add(3, 5);
		System.out.println(list);
		System.out.println(list.remove(3));
		System.out.println(list);
		System.out.println(list.remove(new Integer(3)));
		System.out.println(list);
		list.add(1,600);
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
		
		list.add(3);
		list.add(3);
		list.add(3);
		System.out.println(list);
		list.remove(new Integer(3));
		System.out.println(list);
		
		// list.removeAll(list);
		// System.out.println(list);
		
		// Collection iteration
		for(Integer i : list) {
			System.out.print(i +" ");
			// cannot modify a collection whilst iterating over it.
			// list.remove(i);
		}
		System.out.println();
		
//		Iterator<Integer> iterator = list.iterator();
//		while(iterator.hasNext()) {
//			Integer i = iterator.next();
//			System.out.println(i);
//			if(i==600) {
//				iterator.remove();
//			}
//		}
//		System.out.println(list);
		
		for(ListIterator<Integer> listIterator = list.listIterator(list.size());
				listIterator.hasPrevious();) {
			System.out.println(listIterator.previous());
		}
	}
}
