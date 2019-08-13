package com.revature.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.revature.beans.Bean;

public class ReflectionExample {
	// Reflection is an API for examining and modifiying code at runtime
	public static void main(String[] args) 
		throws NoSuchFieldException, SecurityException, 
		IllegalArgumentException, IllegalAccessException {
		// beans();
		// Are strings actually immutable?
		stringPool();
	}

	private static void stringPool() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String s = "Hello";
		Field[] stringFields = s.getClass().getDeclaredFields();
		for(Field f: stringFields) {
			System.out.println(f);
		}
		Field valueField = s.getClass().getDeclaredField("value");
		valueField.setAccessible(true);
		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(valueField, valueField.getModifiers() & ~Modifier.FINAL);
		char [] c = {'G', 'o', 'o', 'd', 'b', 'y', 'e'};
		valueField.set(s, c);
		
		System.out.println("Hello");
		System.out.println(new String("Hello"));
		System.out.println("H"+"ello");
		String s1 = new String("H");
		String s2 = new String("ello");
		String s3 = new String(s1+s2);
		System.out.println(s3);
		s1 = "H";
		s2= "ello";
		s3 = s1+s2;
		System.out.println(s3);
		s3 = s3.intern();
		System.out.println(s3);
		System.out.println("Hello");
	}

	private static void beans() throws NoSuchFieldException, SecurityException, 
	IllegalArgumentException, IllegalAccessException {
		Class<Bean> beanClass = Bean.class;
		System.out.println(beanClass);
		Bean beanObject = new Bean();
		beanObject.setColor("red");
		beanObject.setStrain("Red Bean");
		//beanObject.color = "green";
		System.out.println(beanObject);
		
		System.out.println("/tDeclared Methods");
		// All the methods declared in the class, excluding inherited
		Method[] methods = beanClass.getDeclaredMethods();
		for(Method m: methods) {
			System.out.println("\t\t"+m);
		}
		System.out.println("\tPublic Methods");
		// All public methods, including inherited
		methods = beanClass.getMethods();
		for(Method m: methods) {
			System.out.println("\t\t"+m);
		}
		System.out.println("\tConstructors");
		Constructor<Bean>[] constructors = (Constructor<Bean>[]) beanClass.getConstructors();
		for(Constructor<Bean> c: constructors) {
			System.out.println("\t\t"+c);
		}
		System.out.println("\tFields");
		Field[] fields = beanClass.getDeclaredFields();
		for(Field f : fields) {
			System.out.println("\t\t"+f);
		}
		
		System.out.println(beanObject);
		Field colorField = beanObject.getClass().getDeclaredField("color");
		colorField.setAccessible(true);
		colorField.set(beanObject, "dog");
		System.out.println(beanObject);
	}
}
