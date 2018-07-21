package com.techstack.spring.service;

import org.springframework.stereotype.Component;

import com.techstack.spring.utils.Helpers;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Component
public class HiByeServiceTraditional2 {
	
	public void sayHi(String name) {
		Helpers.doLog(this.getClass().getSimpleName(), "sayHi", Trace.ENTRY.getValue());
		
		System.out.println("Hi " + name);
		
		Helpers.doLog(this.getClass().getSimpleName(), "sayHi", Trace.EXIT.getValue());
	}

	public void sayBye() {
		Helpers.doLog(this.getClass().getSimpleName(), "sayBye", Trace.ENTRY.getValue());
		
		System.out.println("Bye");
		
		Helpers.doLog(this.getClass().getSimpleName(), "sayBye", Trace.EXIT.getValue());
	}

	public String returnSomething() {
		Helpers.doLog(this.getClass().getSimpleName(), "returnSomething", Trace.ENTRY.getValue());
		
		String value = "Hi Bye";
		
		Helpers.doLog(this.getClass().getSimpleName(), "returnSomething", Trace.EXIT.getValue());
		return value;
	}

	enum Trace{
		ENTRY("entry"),
		EXIT("exit");
		
		private String trace;
		
		Trace(String trace) {
			this.trace = trace;
		}
		
		public String getValue() {
			return this.trace;
		}
	}
}
