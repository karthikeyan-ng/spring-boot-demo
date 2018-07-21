package com.techstack.spring._15.internationalization;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForMessageSource {

	public static void main(String[] args) {

		MessageSource resources = new ClassPathXmlApplicationContext("internationalization.xml");
	    String message = resources.getMessage("message", null, "Default", null);
	    System.out.println(message);
	}

}
