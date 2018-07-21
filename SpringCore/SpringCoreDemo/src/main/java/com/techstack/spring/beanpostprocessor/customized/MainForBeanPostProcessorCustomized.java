package com.techstack.spring.beanpostprocessor.customized;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForBeanPostProcessorCustomized {

	public static void main(String[] args) {

		AbstractApplicationContext context;
		context = new ClassPathXmlApplicationContext("beanPostProcessorCustomized.xml");
		
		Vehicle vehicle = context.getBean(Vehicle.class);
		System.out.println(vehicle.getName());
		context.registerShutdownHook();
	}

}
