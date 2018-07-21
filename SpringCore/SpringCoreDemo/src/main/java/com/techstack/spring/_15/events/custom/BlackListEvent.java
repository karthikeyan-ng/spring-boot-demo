/**
 * 
 */
package com.techstack.spring._15.events.custom;

import org.springframework.context.ApplicationEvent;

/**
 * 
 * You can also create and publish your own custom events. 
 * This example demonstrates a simple class that extends Spring’s ApplicationEvent base class:
 * 
 * @author KARTHIKEYAN N
 *
 */
public class BlackListEvent extends ApplicationEvent {

    private String address;
    private String test;

    public BlackListEvent(Object source, String address, String test) {
        super(source);
        this.address = address;
        this.test = test;
        
        System.out.println("BlackListEvent has been fired...");
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

    // accessor and other methods...
}
