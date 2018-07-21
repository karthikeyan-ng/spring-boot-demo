/**
 * 
 */
package com.techstack.spring._15.events.custom;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * 
 * To publish a custom ApplicationEvent, call the publishEvent() method on an ApplicationEventPublisher. 
 * Typically this is done by creating a class that implements ApplicationEventPublisherAware and registering 
 * it as a Spring bean. The following example demonstrates such a class:
 * 
 * At configuration time, the Spring container will detect that EmailService implements ApplicationEventPublisherAware 
 * and will automatically call setApplicationEventPublisher(). In reality, the parameter passed in will be the Spring 
 * container itself; you’re simply interacting with the application context via its ApplicationEventPublisher interface.
 * 
 * @author KARTHIKEYAN N
 *
 */
@Service
public class PersonEntityService implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher publisher;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ApplicationEventPublisherAware#
	 * setApplicationEventPublisher(org.springframework.context.
	 * ApplicationEventPublisher)
	 */
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	public <Type> void firePresonEvent(Type type) {
		EntityCreatedEvent<Type> event = new EntityCreatedEvent<Type>(type);
		publisher.publishEvent(event);
		return;
	}
}
