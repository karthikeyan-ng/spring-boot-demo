package com.techstack.spring.beanwrapper.builtinpropertyeditor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainAppForCustomPhoneEditor {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("CustomPhoneEditor.xml");
		Customer bean = context.getBean(Customer.class);
		System.out.println(bean);

	}
}
