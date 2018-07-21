package com.techstack.spring.di.dependson.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForDependsOn {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		BeanOne bean = context.getBean(BeanOne.class);
		bean.doSomthing();
		context.close();

	}

}
