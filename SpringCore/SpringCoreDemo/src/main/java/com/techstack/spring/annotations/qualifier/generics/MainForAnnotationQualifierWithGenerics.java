package com.techstack.spring.annotations.qualifier.generics;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForAnnotationQualifierWithGenerics {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		RateCalculator bean = context.getBean(RateCalculator.class);
        bean.calculate();
		
		context.registerShutdownHook();
	}

}
