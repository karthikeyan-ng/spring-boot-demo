package com.techstack.spring.scopes.prototype;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForPrototypeExample {

	public static void main(String[] args) {

		/**
		 * Instantiating a container
		 */
		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("depinjection_prototype.xml");

		AccountService service1 = context.getBean(AccountService.class);
		service1.setAccountHolder("Karthi");
		System.out.println(service1.getAccountHolder());

		AccountService service2 = context.getBean(AccountService.class);
		System.out.println(service2.getAccountHolder());

	}

}
