package com.techstack.spring.lifecycle.startupandshutdown;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainSmartLifeCycleExample {

	public static void main(String[] args) {

		/**
		 * Instantiating a container
		 */
		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("lifecylce_smarlifecycle.xml");

//		AccountService service1 = context.getBean(AccountService.class);

//		AccountService2 service2 = context.getBean(AccountService2.class);

	}

}
