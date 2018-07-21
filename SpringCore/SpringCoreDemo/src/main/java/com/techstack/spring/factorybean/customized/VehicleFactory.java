/**
 * 
 */
package com.techstack.spring.factorybean.customized;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author KARTHIKEYAN N
 *
 */
public class VehicleFactory implements FactoryBean<Vehicle> {

	private int factoryId;
	private int toolId;

	@Override
	public Vehicle getObject() throws Exception {
		return new Vehicle(toolId);
	}

	@Override
	public Class<?> getObjectType() {
		return Vehicle.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public int getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(int factoryId) {
		this.factoryId = factoryId;
	}

	public int getToolId() {
		return toolId;
	}

	public void setToolId(int toolId) {
		this.toolId = toolId;
	}

}
