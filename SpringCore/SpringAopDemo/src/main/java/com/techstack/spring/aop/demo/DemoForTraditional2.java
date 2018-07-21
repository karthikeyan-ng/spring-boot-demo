package com.techstack.spring.aop.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.techstack.spring.configuration.AppConfig;
import com.techstack.spring.service.HiByeServiceTraditional2;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class DemoForTraditional2 {

	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		HiByeServiceTraditional2 service = context.getBean(HiByeServiceTraditional2.class);
		service.sayHi("test");
		service.sayBye();
		service.returnSomething();
	}
}
