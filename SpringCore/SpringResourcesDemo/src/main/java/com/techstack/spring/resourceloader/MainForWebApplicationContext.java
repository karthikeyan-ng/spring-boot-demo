package com.techstack.spring.resourceloader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForWebApplicationContext {

	public static void main(String[] args) {

		ApplicationContext context;
		
		context = new ClassPathXmlApplicationContext("baseConfig.xml");
		//context = new WebApplicationContext();
		
		/**
		 * When you call getResource() on a specific application context, and the location path 
		 * specified doesn’t have a specific prefix, you will get back a Resource type that is 
		 * appropriate to that particular application context. For example, assume the following 
		 * snippet of code was executed against a ClassPathXmlApplicationContext instance:
		 * 
		 * if the same method was executed against a FileSystemXmlApplicationContext instance, 
		 * you’d get back a FileSystemResource.
		 * 
		 * For a WebApplicationContext, you’d get back a ServletContextResource, and so on.
		 */
		Resource template = context.getResource("format.properties");
		System.out.println(template.getClass().getSimpleName());	//Would return WebSystemResource
		
	}

}
