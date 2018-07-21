package com.techstack.spring.beandefinitioninheritance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForBeanDefinitionInheritance {

	public static void main(String[] args) {

		/**
		 * Instantiating a container
		 */
		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("beanDefinitionInheritance.xml");

		TestBean baseClass = context.getBean(TestBean.class);
		System.out.println(baseClass.toString());

		DerivedTestBean childClass = context.getBean(DerivedTestBean.class);
		System.out.println(childClass.toString());
	}

}
