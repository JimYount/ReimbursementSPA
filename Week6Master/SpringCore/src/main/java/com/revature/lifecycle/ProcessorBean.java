package com.revature.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProcessorBean implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if("SteveBob".contentEquals(beanName))
			System.out.println("BeanPostProcessor's beforeInitialization");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if("SteveBob".contentEquals(beanName))
			System.out.println("BeanPostProcessor's afterInitializtion");
		return bean;
	}

}
