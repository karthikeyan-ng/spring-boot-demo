package com.techstack.spring.lifecycle.destruction;

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
		context = new ClassPathXmlApplicationContext("depinjection_destructionCallback.xml");
		
		AccountService service1 = context.getBean(AccountService.class);

		AccountService2 service2 = context.getBean(AccountService2.class);
		
		context.close();

	}

}
