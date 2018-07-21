package com.techstack.spring.di.collections;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techstack.spring.beans.configuration.AppConfig;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForCollectionsDI {

	public static void main(String[] args) {

		/**
		 * Instantiating a container
		 */
		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("depinjection_collections.xml");
		
		ManageCollections collections = context.getBean(ManageCollections.class);
		System.out.println(collections.getEmailFor("support"));
		
		System.out.println("List Collection = " + collections.getListCollection());
		System.out.println("Set Collection = " + collections.getSetCollection());
		System.out.println("Map Collection = " + collections.getMapCollection());
		
	}

}
