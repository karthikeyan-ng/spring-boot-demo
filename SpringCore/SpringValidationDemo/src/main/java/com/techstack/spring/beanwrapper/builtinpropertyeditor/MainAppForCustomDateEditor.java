package com.techstack.spring.beanwrapper.builtinpropertyeditor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainAppForCustomDateEditor {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("CustomDateEditor.xml");
		 
        Employee employee = (Employee) context.getBean(Employee.class);
         
        System.out.println(employee.getDateOfBirth());
		
		
	}
}
