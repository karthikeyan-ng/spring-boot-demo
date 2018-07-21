package com.techstack.spring._15.internationalization;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForMessageSource2 {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("internationalization2.xml");
	    Example example = context.getBean(Example.class);
	    example.execute();
	}

}
