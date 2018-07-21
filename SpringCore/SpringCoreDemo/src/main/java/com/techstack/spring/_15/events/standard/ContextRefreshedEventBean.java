/**
 * 
 */
package com.techstack.spring._15.events.standard;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * This is an old way of implementation before Spring 4.2
 * 
 * @author KARTHIKEYAN N
 *
 */
@Component
public class ContextRefreshedEventBean implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("ContextRefreshedEvent has been fired...");
	}
	
	

}
