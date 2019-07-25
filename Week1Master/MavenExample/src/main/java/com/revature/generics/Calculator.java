package com.revature.generics;

// A Generic is a type that is set at Compile Time.
// The compile actually scrubs all generics out of the
// code-base, replacing them with concrete types.
// Generics allows us to have dynamic classes that can take in
// multiple different types, which increases reusability.
public class Calculator <Bob extends Number> {
	public Bob add (Bob a, Bob b) {
		Number ret = a.doubleValue()+b.doubleValue();
		return round(a, ret);
	}
	public Bob sub(Bob a, Bob b) {
		Number ret = a.doubleValue()-b.doubleValue();
		return round(a, ret);
	}
	public Bob mult(Bob a, Bob b) {
		Number ret = a.doubleValue()*b.doubleValue();
		return round(a, ret);
	}
	public Bob div(Bob a, Bob b) {
		Number ret = a.doubleValue()/b.doubleValue();
		return round(a, ret);
	}
	public Bob add(Bob a, Bob...b) {
		Bob ret = a;
		for(Bob t: b) {
			ret = add(ret, t);
		}
		return ret;
	}
	public Bob sub(Bob a, Bob...b) {
		Bob ret = a;
		for(Bob t: b) {
			ret = sub(ret, t);
		}
		return ret;
	}
	public Bob mult(Bob a, Bob...b) {
		Bob ret = a;
		for(Bob t: b) {
			ret = mult(ret, t);
		}
		return ret;
	}
	public Bob div(Bob a, Bob...b) {
		Bob ret = a;
		for(Bob t: b) {
			ret = div(ret, t);
		}
		return ret;
	}
	private Bob round(Bob a, Number ret) {
		if(a instanceof Float || a instanceof Double)
			return (Bob) ret;
		ret = Math.round(ret.doubleValue());
		return (Bob) ret;
	}
	public void test() {
		throw new IllegalArgumentException();
	}
}
