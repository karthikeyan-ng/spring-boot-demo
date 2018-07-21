package com.techstack.spring.factorybean.customized;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForFactoryBeanCustomized {

	public static void main(String[] args) {

		AbstractApplicationContext context;
		context = new ClassPathXmlApplicationContext("beanUsingFactoryBean.xml");
		
		Vehicle vehicle = context.getBean(Vehicle.class);
		System.out.println(vehicle.getToolId());
		
		context.registerShutdownHook();
	}

}
