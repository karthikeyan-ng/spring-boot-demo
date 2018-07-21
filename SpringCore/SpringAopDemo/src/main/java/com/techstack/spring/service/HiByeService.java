package com.techstack.spring.service;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Component
public class HiByeService {
	
	public void sayHi() {
		System.out.println("Hi, Welcome to AOP Demo");
	}
	
	public void sayHi(String name) {
		System.out.println("Hi " + name);
	}
	
	public void sayHi(String name, String country) {
		System.out.println("Hi " + name + " Welcome to " + country);
	}
	
	public void sayHi(String name, Integer age) {
		System.out.println("Hi " + name + " Your age is " + age);
	}
	
	public void transfer(String name, Integer age, String country) {
		System.out.println("Hi " + name + " Your age is " + age + " Welcome to " + country);
	}
	
	public void test(Date date, Integer year) {
		System.out.println("Today's date is " + date+ " year is " + year);
	}

	public void sayBye() {
		System.out.println("Bye");
	}

	public String returnSomething() {
		return "Hi Bye";
	}
	
}