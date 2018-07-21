package com.techstack.spring.resourceloader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForClassPathResources {

	public static void main(String[] args) {

		ApplicationContext context;
		
		context = new ClassPathXmlApplicationContext("baseConfig.xml");
		
		/**
		 * When you call getResource() on a specific application context, and the location path 
		 * specified doesn’t have a specific prefix, you will get back a Resource type that is 
		 * appropriate to that particular application context. For example, assume the following 
		 * snippet of code was executed against a ClassPathXmlApplicationContext instance:
		 */
//		Resource template = context.getResource("format.properties");
//		System.out.println(template.getClass().getSimpleName());	//Would return ClassPathContextResource
		
		
		/**
		 * On the other hand, you may also force ClassPathResource to be used, regardless of the application context type, 
		 * by specifying the special classpath: prefix:
		 */
		Resource template = context.getResource("classpath:format.properties");
		System.out.println(template.getClass().getSimpleName());	//Would return ClassPathContextResource
		
	}

}
