package com.techstack.spring.utils;

import org.springframework.stereotype.Component;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Component
public class Helpers {
	
	public static void doLog(String classname, String methodname, String trace) {
		System.out.println(classname + "-->" + methodname + " :: " + trace);
	}
	
	public static void doTransaction() {
		
	}
	
	public static void doSecurity() {
		
	}

}
