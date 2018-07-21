package com.techstack.spring.annotations.qualifier.withoutvalue;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForAnnotationQualifierWithoutValue {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MovieRecommender movieRecommender = context.getBean(MovieRecommender.class);
		System.out.println(movieRecommender.printOfflineCatalog());
		
		context.registerShutdownHook();
	}

}
