package com.techstack.spring.beanpostprocessor.customized.usingordered;


public class Restaurant {

	public void greetCustomer() {

		System.out.println("Welcome dear customer!! This is the LifeCyce demo");
	}

	public void init() {

		System.out.println("Bean is going through init.");
	}

	public void destroy() {

		System.out.println("Bean is getting destroyed.");
	}

}