/**
 * 
 */
package com.techstack.spring.beanwrapper.builtinpropertyeditor;

/**
 * @author KARTHIKEYAN N
 *
 */
public class Phone {

	private String phoneNumber;
	private PhoneType phoneType;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	@Override
	public String toString() {
		return "Phone{" + "phoneNumber='" + phoneNumber + '\'' + ", phoneType=" + phoneType + '}';
	}
}
