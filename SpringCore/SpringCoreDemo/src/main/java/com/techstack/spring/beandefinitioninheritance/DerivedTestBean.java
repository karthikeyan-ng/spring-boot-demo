/**
 * 
 */
package com.techstack.spring.beandefinitioninheritance;

/**
 * @author KARTHIKEYAN N
 *
 */
public class DerivedTestBean extends TestBean {
	
	private String name;
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	public void initialize() {
		this.name = "karthi";
	}
	
	@Override
	public String toString() {
		return "Name="+getName()+"\tAge="+getAge();
	}
}
