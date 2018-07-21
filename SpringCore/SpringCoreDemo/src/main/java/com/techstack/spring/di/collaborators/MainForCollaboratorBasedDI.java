package com.techstack.spring.di.collaborators;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techstack.spring.beans.configuration.AppConfig;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForCollaboratorBasedDI {

	public static void main(String[] args) {

		/**
		 * Instantiating a container
		 */
		ApplicationContext parentContext;
		parentContext = new ClassPathXmlApplicationContext("depinjection_collaboratorParent.xml");
		
		
		/**
		 * if not used parentContext then, Spring would throw an Exception as show below.
		 * 
		 * Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'id1' 
		 * defined in class path resource [depinjection_collaboratorChild.xml]: Can't resolve reference to bean 
		 * 'sampleBean' in parent factory: no parent factory available
		 */
		ApplicationContext childContext;
		childContext = new ClassPathXmlApplicationContext(new String[]{"depinjection_collaboratorChild.xml"}, parentContext);
		
		DemoBean demo = childContext.getBean(DemoBean.class);
		demo.m1();
	}

}
