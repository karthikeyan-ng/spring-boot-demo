package com.techstack.spring._15.events.standard;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForStandardEvent {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.start();		//ContextStartedEvent
		//context.refresh();	//ContextRefreshedEvent
		
		context.stop();			//ContextStoppedEvent
		
		context.close();		//ContextClosedEvent

	}

}
