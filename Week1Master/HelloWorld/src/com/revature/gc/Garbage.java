package com.revature.gc;

public class Garbage {
	private String name;
	
	public Garbage(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Garbage [name="+name+"]";
	}
	
	/*
	 * Finalize has been deprecated as of Java 9
	 * 
	 * Finalize:
	 * 		Finalize is a method on every object that is Called BY GC
	 * 		As a final chance to close resources prior to memory
	 * 		being freed.
	 * 
	 * It is a courtesy call. It is one last chance, one last gasp
	 * 		that GC gives you before it deletes something.
	 * 
	 * FINALIZE DOES NOTHING
	 * FINALIZE SHOULD NOT BE USED
	 */
	@Override
	protected void finalize() throws Throwable {
		// last gasp before collection
		System.out.println(this);
		super.finalize();
	}
}
