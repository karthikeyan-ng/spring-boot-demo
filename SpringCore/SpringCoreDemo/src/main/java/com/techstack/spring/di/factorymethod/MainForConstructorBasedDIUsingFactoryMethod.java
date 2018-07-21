package com.techstack.spring.di.factorymethod;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techstack.spring.beans.configuration.AppConfig;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForConstructorBasedDIUsingFactoryMethod {

	public static void main(String[] args) {

		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("depinjection_constructorUsingFactoryMethod.xml");
	
		Employee manager = context.getBean("manager", Employee.class);
        System.out.println(manager);
         
        Employee director = context.getBean("director", Employee.class);
        System.out.println(director);
	}

}
