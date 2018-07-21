package com.techstack.spring._15.events.custom;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForClassCustomEvent {

	public static void main(String[] args) {

		ApplicationContext context;
		
		//context = new ClassPathXmlApplicationContext("customEvents.xml");
		
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		//EmailService emailService = context.getBean(EmailService.class);
		//emailService.sendEmail("known.spammer@example.org", "Hi, You are black listed...");
		
		//test for conditional expression 
		//emailService.sendEmail("known.spammer@example.org", "foo");
		
		Person person = context.getBean(Person.class);
		PersonEntityService personService = context.getBean(PersonEntityService.class);
		personService.firePresonEvent(person);
		
		
	}

}
