/**
 * 
 */
package com.techstack.spring.beanwrapper.builtinpropertyeditor;

/**
 * @author KARTHIKEYAN N
 *
 */
public class Customer {

	private String customerName;
	private Phone phone;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Customer{" + "customerName='" + customerName + '\'' + ", phone=" + phone + '}';
	}

}
