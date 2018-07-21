/**
 * 
 */
package com.techstack.spring.beanpostprocessor.customized;

/**
 * @author KARTHIKEYAN N
 *
 */
public class Vehicle {

	private String name;

	public void init() {
		System.out.println("Inside init method");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void destroy() {
		System.out.println("Inside destroy method");
	}

}
