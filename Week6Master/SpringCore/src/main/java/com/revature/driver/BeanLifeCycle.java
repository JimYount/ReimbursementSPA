package com.revature.driver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.lifecycle.LifeBean;

public class BeanLifeCycle {
	public static void main(String[] args) {
		// Running this application will give you the entire Spring Bean LifeCycle
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		LifeBean b = (LifeBean) ac.getBean(LifeBean.class);
		b.use();
		((AbstractApplicationContext) ac).close();
	}
}
