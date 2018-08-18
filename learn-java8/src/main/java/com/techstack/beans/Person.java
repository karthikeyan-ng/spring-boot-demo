/**
 * 
 */
package com.techstack.beans;

/**
 * @author KARTHIKEYAN N
 *
 */
public class Person {

	private int id;
	private String name;
	private int age;
	private String gender;
	
	public Person(int id, String name, String gender, int age) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return getId() + " " + getName() + " " + getGender() + " " + getAge();
	}
}
