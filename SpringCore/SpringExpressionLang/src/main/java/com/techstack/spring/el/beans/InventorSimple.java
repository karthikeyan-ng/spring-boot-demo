/**
 * 
 */
package com.techstack.spring.el.beans;

import java.util.Date;

/**
 * @author KARTHIKEYAN N
 *
 */
public class InventorSimple {

	private String name;
	
	private Date birthday;
	
	private String nationality;
	
	public InventorSimple(String name, Date birthday, String nationality) {
		this.name = name;
		this.birthday = birthday;
		this.nationality = nationality;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
}
