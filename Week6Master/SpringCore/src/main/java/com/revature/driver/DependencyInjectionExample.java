package com.revature.driver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.House;
import com.revature.beans.WallImpl;

public class DependencyInjectionExample {
	public static ApplicationContext ac;

	public static void main(String[] args) {
		ac = new ClassPathXmlApplicationContext("beans.xml");
		// ac = new FileSystemXmlApplicationContext("src/main/resources/beans.xml");
		// ac = new XmlWebApplicationContext("beans.xml"); // WEB-INF folder

		// noInjection();
		//withInjection();
		annotations();
	}

	private static void annotations() {
		House h1 = (House) ac.getBean(House.class);
		House h2 = (House) ac.getBean(House.class);
		System.out.println(h1);
		System.out.println(h2);
		System.out.println(h1==h2);
		h2.setName("Big House");
		System.out.println(h1);
		System.out.println(h2);
	}

	private static void withInjection() {
		System.out.println(ac.getBean("cards"));
		// Anytime we use the new keyword, we are not using spring
		// Spring manages the lifecycle of our beans
		// the new keyword is the start of that lifecycle.
		// by calling new, we take the lifecycle out of the container.

		// For a bean to be managed by spring, it must be created in the container.
		// The ApplicationContext is our container (it used to be the BeanFactory)
		House h1 = (House) ac.getBean("cards");
		System.out.println(h1);
		House h2 = (House) ac.getBean("cards");
		System.out.println(h2);
		System.out.println("h1==h2:"+(h1==h2));
		h1.setNumWalls(57);
		System.out.println(h1);
		System.out.println(h2);
		System.out.println(ac.getBean("cards"));
		
		System.out.println(ac.getBean("wall"));
		System.out.println(ac.getBean("wall") == h1.getWall());
	}

	private static void noInjection() {
		House h = new House();
		System.out.println(h);
		h.setWall(new WallImpl());
		System.out.println(h);
		h.getWall().setMaterial("brick");
		System.out.println(h);
		// If you call the new keyword... you are not using Spring.
	}
}
