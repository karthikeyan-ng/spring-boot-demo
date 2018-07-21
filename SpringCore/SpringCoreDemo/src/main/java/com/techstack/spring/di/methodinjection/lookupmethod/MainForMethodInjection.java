package com.techstack.spring.di.methodinjection.lookupmethod;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForMethodInjection {

	public static void main(String[] args) {

		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("depinjection_methodInjection.xml");
		
		System.out.println("After initialization");

		//TODO: Code is not completed. Revisit this topic later
	}

}
