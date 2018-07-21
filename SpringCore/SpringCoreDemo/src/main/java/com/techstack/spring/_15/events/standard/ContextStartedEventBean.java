/**
 * 
 */
package com.techstack.spring._15.events.standard;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * This is an old way of implementation before Spring 4.2
 * 
 * @author KARTHIKEYAN N
 *
 */
@Component
public class ContextStartedEventBean implements ApplicationListener<ContextStartedEvent> {

	@Override
	public void onApplicationEvent(ContextStartedEvent event) {
		System.out.println("Context Started Event has been fired...");
	}

}
