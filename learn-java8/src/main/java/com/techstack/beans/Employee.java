/**
 * 
 */
package com.techstack.beans;

import java.io.Serializable;

/**
 * @author KARTHIKEYAN N
 *
 */
public class Employee implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Double salary;
	
	public Employee() {
		
	}

	public Employee(Integer id, String name, Double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public void salaryIncrement(Double percentage) {
		Double newSalary = salary + percentage * salary / 100;
		setSalary(newSalary);
	}

	@Override
	public String toString() {
		return getId() + ", " + getName() + ", " + getSalary();
	}
	
	@Override
	public boolean equals(Object other) {
		if ((other != null) && (other instanceof Employee)) {
			return ((Employee) other).getId() == getId();
		} else {
			return super.equals(other);
		}
	}
	
	@Override
	public int hashCode() {
		return 23*3*(this.id == null ? 1 : this.id.hashCode()) 
				+ 23*7*(this.name == null ? 1 : this.name.hashCode()) 
				+ 23*13*(this.salary == null ? 1 : this.salary.hashCode());
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Employee emp = new Employee(null, this.name, this.salary);
		return emp;
	}

}
