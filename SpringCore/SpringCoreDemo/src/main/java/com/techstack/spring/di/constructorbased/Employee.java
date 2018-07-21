/**
 * 
 */
package com.techstack.spring.di.constructorbased;

import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
//@Component
public class Employee {

//	private HomeAddress homeAddress;
//	private HomeAddress homeAddress1;
//	private WorkAddress workAddress;
	
	/**
	 * No potential ambiguity exists, assuming that HomeAddress and WorkAddress classes are not related by inheritance. 
	 * Thus the following configuration works fine, and you do not need to specify the constructor 
	 * argument indexes and/or types explicitly in the <constructor-arg/> element.
	 * 
	 * @param homeAddress
	 * @param workAddress
	 */
//	public Employee(HomeAddress homeAddress, WorkAddress workAddress) {
//		this.homeAddress = homeAddress;
//		this.workAddress = workAddress;
//	}
	
//	public Employee(HomeAddress homeAddress, HomeAddress homeAddress1) {
//		this.homeAddress = homeAddress;
//		this.homeAddress1 = homeAddress1;
//	}
	
	private Address homeAddress;
	private Address workAddress;
	
	public Employee(Address homeAddress, Address workAddress) {
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
	}
	
	public void printHomeAddress() {
		this.homeAddress.printInfo();
	}
	
	public void printHomeAddress(String info) {
		System.out.print(info);
		this.homeAddress.printInfo();
	}
	
	public void printWorkAddress() {
		this.workAddress.printInfo();
	}
	
}
