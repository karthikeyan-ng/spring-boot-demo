package com.techstack.spring.beanfactorypostprocessor.customized.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForBeanFactoryPostProcessorCustomized {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		CredentialsInfo credentials = context.getBean(CredentialsInfo.class);
		System.out.println(credentials.getUsername());
		context.registerShutdownHook();
	}

}
