/**
 * 
 */
package com.techstack.spring.di.dependson.annotation;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author KARTHIKEYAN N
 *
 */
public class BeanOne {

	@Autowired
	private BeanTwo beanTwo;

	@Autowired
	private BeanThree beanThree;

	public BeanOne() {
		System.out.println("BeanOne Initialized");
	}

	public void doSomthing() {
		System.out.println("Inside doSomthing() method of BeanOne");
		beanTwo.doSomthing();
		beanThree.doSomthing();
	}

}
