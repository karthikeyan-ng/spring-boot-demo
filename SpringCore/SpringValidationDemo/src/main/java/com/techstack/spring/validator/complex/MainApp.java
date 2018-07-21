package com.techstack.spring.validator.complex;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * While it is certainly possible to implement a single Validator class to validate each of the nested objects in a rich object.
 * But, it may be better to encapsulate the validation logic for each nested class of object in its own Validator implementation.
 * 
 * Example:
 * 	- a 'rich' object would be a Customer that is composed of two String properties (a first and second name) and a complex Address object.
 *  - Address objects may be used independently of Customer objects, and so a distinct AddressValidator has been implemented.
 *  - If you want your CustomerValidator to reuse the logic contained within the AddressValidator class without resorting to copy-and-paste, 
 *  you can dependency-inject or instantiate an AddressValidator within your CustomerValidator, and use it
 *	
 * - Customer.java (contains Address ref)
 * - Address.java 
 * - CustomerValidator.java
 * - AddressValidator.java
 * - CustomerService.java to do validation for both Customer and Address
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainApp {

	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		//Customer type contains a Address type
		Customer customer = context.getBean(Customer.class);
		customer.setFirstname(""); //Pascal
		customer.setSecondname(""); //Krahmer
		
		Address address = context.getBean(Address.class);
		address.setStreetName("Wilson Street");
		address.setCity("NJ");
		address.setZipcode(601);
		customer.setAddress(address);
		
		CustomerService customerService = context.getBean(CustomerService.class);
		customerService.doPreValidator(customer);
		
	}
}
