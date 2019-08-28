package com.revature.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.revature.beans.Wall;

@Component("SteveBob")
public class LifeBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware,
				InitializingBean, DisposableBean {
	private Wall wall;
	
	public LifeBean() {
		super();
		System.out.println("Instantiation. (Constructor Injection)");
	}
	@Autowired
	public void setWall(Wall w) {
		System.out.println("Populate Properties. (Setter Injection)");
	}
	public void use() {
		System.out.println("The bean is in use. (I'm actually using it for stuff)");
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("ApplicationContextAware's setApplicationContext: "+applicationContext);
	}
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("BeanFactoryAware's setBeanFactory: "+beanFactory);
	}
	@Override
	public void setBeanName(String name) {
		System.out.println("BeanNameAware's setBeanName: "+name);
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean's afterPropertiesSet()");
	}
	@PostConstruct
	public void customInitializationNameDoesNotMatterBob() {
		System.out.println("PostConstruct, custom initialization method");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean's destroy()");
	}
	@PreDestroy
	public void customDestroy() {
		System.out.println("PreDestroy, custom destroy");
	}
}
