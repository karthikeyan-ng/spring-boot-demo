/**
 * 
 */
package com.techstack.spring.factorybean.customized;

/**
 * @author KARTHIKEYAN N
 *
 */
public class Vehicle {
	
	private int toolId;

	public Vehicle(int toolId) {
		this.toolId = toolId;
	}

	public int getToolId() {
		return toolId;
	}

	public void setToolId(int toolId) {
		this.toolId = toolId;
	}

}
