/**
 * 
 */
package com.techstack.spring.beanwrapper.builtinpropertyeditor;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
@Component
public class Employee {

	private Integer id;
	private String firstName;
	private String lastName;
	private String designation;
	private Date dateOfBirth;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
    public String toString() {
        return "EmployeeDTO [id=" + id + ", firstName=" + firstName
                + ", lastName=" + lastName + ", designation=" + designation
                + ", dateOfBirth=" + dateOfBirth + "]";
    }

}
