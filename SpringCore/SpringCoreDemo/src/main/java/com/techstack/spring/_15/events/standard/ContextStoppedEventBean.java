/**
 * 
 */
package com.techstack.spring._15.events.standard;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

/**
 * This is an old way of implementation before Spring 4.2
 * 
 * @author KARTHIKEYAN N
 *
 */
@Component
public class ContextStoppedEventBean implements ApplicationListener<ContextStoppedEvent> {

	@Override
	public void onApplicationEvent(ContextStoppedEvent event) {
		System.out.println("Context Stopped Event has been fired...");		
	}

}
