package com.techstack.spring.beanpostprocessor.customized.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForBeanPostProcessorUsingAnnotation {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		VehicleService service = context.getBean(VehicleService.class);
		service.print();
		context.registerShutdownHook();
	}

}
