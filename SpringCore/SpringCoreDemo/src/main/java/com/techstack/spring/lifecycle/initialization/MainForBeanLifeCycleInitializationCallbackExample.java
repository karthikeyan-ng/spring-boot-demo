package com.techstack.spring.lifecycle.initialization;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForBeanLifeCycleInitializationCallbackExample {

	public static void main(String[] args) {

		/**
		 * Instantiating a container
		 */
		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("depinjection_initializationCallback.xml");

//		AccountService service1 = context.getBean(AccountService.class);

//		AccountService2 service2 = context.getBean(AccountService2.class);

	}

}
