package com.techstack.spring.lifecycle.combine;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForCombineBeanLifeCycleExample {

	public static void main(String[] args) {

		/**
		 * Instantiating a container
		 */
		ConfigurableApplicationContext context;
		context = new ClassPathXmlApplicationContext("depinjection_lifecyclecombine.xml");

		AccountService service = context.getBean(AccountService.class);
		service.getAccountHolder();

		context.close();
	}

}
