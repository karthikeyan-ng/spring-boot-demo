package com.techstack.spring.scopes.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForWebScopeExample {

	public static void main(String[] args) {

		/**
		 * Instantiating a container
		 */
		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("depinjection_webscopes.xml");

		/**
		 * java.lang.IllegalStateException: No Scope registered for scope name 'request'
		 * 
		 * request scope is not allowed to use in standalone bean context
		 * 
		 * to use request, session, application and websocket scope use it in web application
		 */
		
		AccountService service1 = context.getBean(AccountService.class);
		service1.setAccountHolder("Karthi");
		System.out.println(service1.getAccountHolder());

		AccountService service2 = context.getBean(AccountService.class);
		System.out.println(service2.getAccountHolder());

	}

}
