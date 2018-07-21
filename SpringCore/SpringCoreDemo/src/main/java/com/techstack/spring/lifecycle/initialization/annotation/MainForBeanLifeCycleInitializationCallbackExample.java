package com.techstack.spring.lifecycle.initialization.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		AccountService service1 = context.getBean(AccountService.class);


	}

}
