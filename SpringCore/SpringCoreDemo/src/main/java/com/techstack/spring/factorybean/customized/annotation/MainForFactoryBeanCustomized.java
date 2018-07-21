package com.techstack.spring.factorybean.customized.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForFactoryBeanCustomized {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		Vehicle vehicle = context.getBean(Vehicle.class);
		System.out.println(vehicle.getToolId());
		
		context.registerShutdownHook();
	}

}
