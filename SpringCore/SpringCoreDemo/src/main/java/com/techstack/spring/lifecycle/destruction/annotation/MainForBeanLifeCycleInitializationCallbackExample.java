package com.techstack.spring.lifecycle.destruction.annotation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForBeanLifeCycleInitializationCallbackExample {

	public static void main(String[] args) {

		/**
		 * The ConfigurableApplicationContext.close will close the application context, 
		 * releasing all resources and destroying all cached singleton beans.
		 */
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		AccountService service1 = context.getBean(AccountService.class);

		context.close();
	}

}
