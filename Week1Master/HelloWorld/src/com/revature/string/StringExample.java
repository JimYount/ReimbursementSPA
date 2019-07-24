package com.revature.string;

public class StringExample {
	public static void main(String[] args) {
		// stringPool();
		// mutability();
		stringBuilder();
	}
	
	// String		|	StringBuilder	|	StringBuffer
	//-----------------------------------------------------
	// final		|	final			|	final
	// immutable	|	mutable			|	mutable
	// thread-safe	|	not thread-safe	|	thread-safe
	// fast			|	fast			|	slow
	public static void stringBuilder() {
		StringBuilder sb = new StringBuilder("Hello");
		sb.append(" World");
		sb.reverse();
		System.out.println(sb.toString());
		
		String s = "Hello World";
		char c[] = s.toCharArray();
		for(int i = 0; i<c.length/2; i++) {
			char temp = c[i];
			c[i] = c[c.length-(i+1)];
			c[c.length-(i+1)]=temp;
		}
		s = new String(c);
		System.out.println(s);
	}
	public static void mutability() {
		String s = "Hello";
		System.out.println(s=="Hello");
		s = s + "Goodbye";
		System.out.println(s);
		System.out.println(s=="Hello");
	}

	public static void stringPool() {
		String s1 = "Hello";
		String s2 = "Hello";
		String s3 = new String("Hello");
		
		System.out.println("s1==s2: "+(s1==s2));
		System.out.println("s1==s3: "+(s1==s3));
		System.out.println("s1.equals(s3): "+s1.equals(s3));
		
		s3 = s3.intern();
		System.out.println("s==s3: "+(s1==s3));
	}
}
