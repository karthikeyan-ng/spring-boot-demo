package com.techstack.spring.annotations.qualifier.multiplequalifierparams;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForAnnotationQualifier {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MovieRecommender movieRecommender = context.getBean(MovieRecommender.class);
		
		context.registerShutdownHook();
	}

}
