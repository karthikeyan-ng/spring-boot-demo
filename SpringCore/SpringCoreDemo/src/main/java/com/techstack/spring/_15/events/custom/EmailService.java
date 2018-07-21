/**
 * 
 */
package com.techstack.spring._15.events.custom;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

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
public class EmailService implements ApplicationEventPublisherAware {

	private List<String> blackList;
	
	private ApplicationEventPublisher publisher;

	public void setBlackList(List<String> blackList) {
		this.blackList = blackList;
	}

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

	public void sendEmail(String address, String text) {
		if (blackList.contains(address)) {
			BlackListEvent event = new BlackListEvent(this, address, text);
			publisher.publishEvent(event);
			return;
		}
		// send email...
	}
}
