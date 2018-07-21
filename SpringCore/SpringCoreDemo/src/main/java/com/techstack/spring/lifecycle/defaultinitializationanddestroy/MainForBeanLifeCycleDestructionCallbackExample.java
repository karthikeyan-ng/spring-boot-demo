package com.techstack.spring.lifecycle.defaultinitializationanddestroy;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForBeanLifeCycleDestructionCallbackExample {

	public static void main(String[] args) {

		/**
		 * The ConfigurableApplicationContext.close will close the application context, 
		 * releasing all resources and destroying all cached singleton beans.
		 */
		ConfigurableApplicationContext context;
		context = new ClassPathXmlApplicationContext("depinjection_defaultInitializationAndDestruction.xml");
		
		Account account = context.getBean(Account.class);

		Customer customer = context.getBean(Customer.class);
		
		context.close();

	}

}
