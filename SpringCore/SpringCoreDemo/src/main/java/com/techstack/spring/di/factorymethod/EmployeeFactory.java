/**
 * 
 */
package com.techstack.spring.di.factorymethod;

/**
 * @author KARTHIKEYAN N
 *
 */
public class EmployeeFactory {

	public static Employee createEmployeeOfType(String type) {
		if ("manager".equals(type) || "director".equals(type)) {
			Employee employee = new Employee();

			employee.setId(-1);
			employee.setFirstName("dummy");
			employee.setLastName("dummy");
			// Set designation here
			employee.setDesignation(type);

			return employee;
		} else {
			throw new IllegalArgumentException("Unknown product");
		}
	}

}
