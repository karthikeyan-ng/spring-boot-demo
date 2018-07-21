package com.techstack.spring.di.dependson;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForDependsOn {

	public static void main(String[] args) {

		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("depinjection_dependson.xml");

		BeanOne bean = context.getBean(BeanOne.class);
		bean.doSomthing();

	}

}
