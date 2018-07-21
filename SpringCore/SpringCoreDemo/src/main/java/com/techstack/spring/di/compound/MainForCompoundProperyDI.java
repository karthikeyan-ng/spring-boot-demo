package com.techstack.spring.di.compound;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForCompoundProperyDI {

	public static void main(String[] args) {

		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("depinjection_compoundProperty.xml");
		
		Bar bar = context.getBean(Bar.class);
		System.out.println(bar.getFoo().getFred().getBob().getSammy());
		
	}

}
