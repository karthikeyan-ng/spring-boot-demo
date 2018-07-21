/**
 * 
 */
package com.techstack.spring.di.dependson;

/**
 * @author KARTHIKEYAN N
 *
 */
public class BeanOne {

	private BeanTwo beanTwo;

	private BeanThree beanThree;

	public BeanOne() {
		System.out.println("BeanOne Initialized");
	}
	
	public void setBeanTwo(BeanTwo beanTwo) {
		this.beanTwo = beanTwo;
	}
	
	public void setBeanThree(BeanThree beanThree) {
		this.beanThree = beanThree;
	}

	public void doSomthing() {
		System.out.println("Inside doSomthing() method of BeanOne");
		beanTwo.doSomthing();
		beanThree.doSomthing();
	}

}
