/**
 * 
 */
package com.techstack.spring.lifecycle.defaultinitializationanddestroy;

/**
 * @author KARTHIKEYAN N
 *
 */
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
	
	public void cleanup() {
		// do some destruction work (like releasing pooled connections)
		System.out.println("simple destroy-method process for " + this.getClass().getSimpleName());
	}
}
