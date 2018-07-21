package com.techstack.spring.di.circular;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techstack.spring.beans.configuration.AppConfig;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForConstructorCircularDI {

	public static void main(String[] args) {

		/**
		 * Instantiating a container
		 */
		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("depinjection_circular.xml");
		
	}

}
