package com.techstack.spring.validator.simple;

import org.springframework.stereotype.Component;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Component
public class Person {

	private String firstname;
	
	private int age;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
