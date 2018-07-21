package com.techstack.spring.validator.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Service	
public class PersonService {
	
	@Autowired @Qualifier("personValidator")
	private Validator validator;
	
	public void doPreValidator(Person person) {
//		BindingResult result = new BeanPropertyBindingResult(person, "person");
		
		//another version
		Errors result = new BeanPropertyBindingResult(person, "person");
		
		//Apply validator
		validator.validate(person, result);
		
		//print failure result
		System.out.println(result);
	}

}
