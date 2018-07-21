/**
 * 
 */
package com.techstack.spring.account.entity;

import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
//@Component
public class Customer {

	private String firstname;
	
	private String secondname;
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getSecondname() {
		return secondname;
	}
	
	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}
	
}
