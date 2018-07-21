package com.techstack.spring.annotations.primary;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForAnnotationPrimary {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MovieRecommender movieRecommender = context.getBean(MovieRecommender.class);
		System.out.println(movieRecommender.printCurrentCatalog());
		
		context.registerShutdownHook();
	}

}
