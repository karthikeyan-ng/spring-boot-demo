package com.techstack.spring._15.internationalization;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForMessageSource3 {

	public static void main(String[] args) {

		MessageSource resources = new ClassPathXmlApplicationContext("internationalization.xml");
		String message = resources.getMessage("argument.required", new Object[] { "userDao" }, "Required", Locale.UK);
		System.out.println(message);
	}

}
