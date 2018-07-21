package com.techstack.spring.validator.complex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Service
public class CustomerService {

	@Autowired @Qualifier("customerValidator")
	private Validator validator;
	
	public void doPreValidator(Customer customer) {
		
		BindingResult result = new BeanPropertyBindingResult(customer, "customer");
		validator.validate(customer, result);
		System.out.println(result);
	}
	
	
}
