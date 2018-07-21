package com.techstack.spring.aop.demo;

import com.techstack.spring.service.HiByeServiceTraditional;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class DemoForTraditional {

	public static void main(String[] args) {

		HiByeServiceTraditional service = new HiByeServiceTraditional();
		service.sayHi("Hoody");
		service.sayBye();
		service.returnSomething();
	}

}
