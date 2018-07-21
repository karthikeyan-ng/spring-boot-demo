/**
 * 
 */
package com.techstack.spring.di.lazyinit;

/**
 * @author KARTHIKEYAN N
 *
 */
public class BeanThree {

	public BeanThree() {
		System.out.println("BeanThree Initialized");
	}

	public void doSomthing() {
		System.out.println("Inside doSomthing() method of BeanThree");
	}

}
