package com.techstack.spring.validator.simple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Simple Spring Validator would validate Person object info.
 * 
 *  1. Person is a simple POJO with members
 *  2. PersonService is a service to call validation
 *  3. PersonValidator to do basic validation
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainApp {

	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		//Simple PersonValidator for Person object (no separate artifact required)
		Person person = context.getBean(Person.class);
		person.setAge(-2);
		person.setFirstname(null);
		
		PersonService personService = context.getBean(PersonService.class);
		personService.doPreValidator(person);
		
	}
}
