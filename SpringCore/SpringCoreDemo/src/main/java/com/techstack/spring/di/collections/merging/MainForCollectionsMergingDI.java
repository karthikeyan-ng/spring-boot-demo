package com.techstack.spring.di.collections.merging;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techstack.spring.beans.configuration.AppConfig;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForCollectionsMergingDI {

	public static void main(String[] args) {

		/**
		 * Instantiating a container
		 */
		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("depinjection_collectionsMerging.xml");
		
		ChildBean childBean = context.getBean(ChildBean.class);
		System.out.println(childBean.getAdminEmails());
		System.out.println(childBean.getEmailFor("support"));
		
	}

}
