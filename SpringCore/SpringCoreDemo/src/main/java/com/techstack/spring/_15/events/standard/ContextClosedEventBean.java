/**
 * 
 */
package com.techstack.spring._15.events.standard;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * This is an old way of implementation before Spring 4.2
 * 
 * @author KARTHIKEYAN N
 *
 */
@Component
public class ContextClosedEventBean implements ApplicationListener<ContextClosedEvent> {

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		System.out.println("Context Closed Event has been fired...");		
	}

}
