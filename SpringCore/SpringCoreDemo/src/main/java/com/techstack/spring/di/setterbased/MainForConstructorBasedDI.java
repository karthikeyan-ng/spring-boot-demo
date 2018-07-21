package com.techstack.spring.di.setterbased;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techstack.spring.beans.configuration.AppConfig;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForConstructorBasedDI {

	public static void main(String[] args) {

		/**
		 * Instantiating a container
		 */
		ApplicationContext context;
		
		context = new ClassPathXmlApplicationContext("depinjection_setterBased.xml");
//==================================
		/**
		 * setter-based dependency injection3
		 */
		SimpleMovieLister movieListener = context.getBean(SimpleMovieLister.class);
		System.out.println(movieListener.findMovieById(101));
//==================================
		
		
		
		/**
		 * Simple Employee bean constructor args of different types
		 * Result is OK!
		 */
//		Employee employee = context.getBean(Employee.class);
//		employee.printHomeAddress();
//		employee.printWorkAddress();
		
		/**
		 * Employee bean constructor args of same types
		 * Result is OK!
		 */
//		Employee employee = context.getBean(Employee.class);
//		employee.printHomeAddress("My Home address is, ");
//		employee.printHomeAddress("My another Home address is, ");
		
		/**
		 * Employee bean has homeAddress and workAddress properties which 
		 * intern refers Address interface
		 * Result is OK!
		 */
//		Employee employee = context.getBean(Employee.class);
//		employee.printHomeAddress();
//		employee.printWorkAddress();
		
//		ExampleBean exampleBean = context.getBean(ExampleBean.class);
//		exampleBean.print();
	}

}
