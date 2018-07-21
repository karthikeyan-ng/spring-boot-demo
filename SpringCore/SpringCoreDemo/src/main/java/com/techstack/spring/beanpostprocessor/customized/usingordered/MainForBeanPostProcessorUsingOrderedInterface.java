package com.techstack.spring.beanpostprocessor.customized.usingordered;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainForBeanPostProcessorUsingOrderedInterface {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("beanPostProcessorCustomizedUsingOrdered.xml");
		
		((AbstractApplicationContext)context).registerShutdownHook();
		
		Restaurant restaurantObj = context.getBean(Restaurant.class);
		restaurantObj.greetCustomer();

	}
}