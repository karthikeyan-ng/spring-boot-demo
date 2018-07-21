package com.techstack.spring.resourceloader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForFileSystemResources {

	public static void main(String[] args) {

ApplicationContext context;
		
		context = new FileSystemXmlApplicationContext("C:\\Workspaces\\SPRING_Workspace\\SpringCore\\SpringResourcesDemo\\src\\main\\resources\\baseConfig.xml");
		
		/**
		 * When you call getResource() on a specific application context, and the location path 
		 * specified doesn’t have a specific prefix, you will get back a Resource type that is 
		 * appropriate to that particular application context. For example, assume the following 
		 * snippet of code was executed against a ClassPathXmlApplicationContext instance:
		 * 
		 * if the same method was executed against a FileSystemXmlApplicationContext instance, 
		 * you’d get back a FileSystemResource.
		 * 
		 * IF NO PREFIX (classpath: or file: or http: or ftp:) Depends on the underlying ApplicationContext.
		 */
//		Resource template = context.getResource("format.properties");
//		System.out.println(template.getClass().getSimpleName());	//Would return FileSystemResource
		
		/**
		 * Similarly, one can force a UrlResource to be used by specifying any of the standard java.net.URL prefixes:
		 * Loaded as a URL, from the filesystem.
		 */
//		Resource template = context.getResource("file:///C://Workspaces//SPRING_Workspace//SpringCore//SpringResourcesDemo//src//main//java//format.properties");
//		System.out.println(template.getClass().getSimpleName());	//Would return UrlResource
		
		/**
		 * Loaded as a URL.
		 */
		Resource template = context.getResource("https://www.google.co.in");
		System.out.println(template.getClass().getSimpleName());	//Would return UrlResource
	}

}
