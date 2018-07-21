/**
 * 
 */
package com.techstack.spring.validator.complex;

import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
@Component
public class Customer {

	private String firstname;
	
	private String secondname;
	
	private Address address;
	
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
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
}
