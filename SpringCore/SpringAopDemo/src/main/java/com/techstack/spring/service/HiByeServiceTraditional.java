package com.techstack.spring.service;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class HiByeServiceTraditional {
	
	public void sayHi(String name) {
		System.out.println(this.getClass().getSimpleName() + "-->sayHi :: Entry");
		
		System.out.println("Hi " + name);
		
		System.out.println(this.getClass().getSimpleName() + "-->sayHi :: Exit");
	}

	public void sayBye() {
		System.out.println(this.getClass().getSimpleName() + "-->sayBye :: Entry");
		
		System.out.println("Bye");
		
		System.out.println(this.getClass().getSimpleName() + "-->sayBye :: Exit");
	}

	public String returnSomething() {
		System.out.println(this.getClass().getSimpleName() + "-->returnSomething :: Entry");
		
		String value = "Hi Bye";
		
		System.out.println(this.getClass().getSimpleName() + "-->returnSomething :: Exit");
		return value;
	}

}
