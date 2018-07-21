package com.techstack.spring.validator.simple;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Component(value = "personValidator")
public class PersonValidator implements Validator {

	/**
	 * This Validator validates *just* Person instances
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		
		/**
		 * (-) As you can see, the static rejectIfEmpty(..) method on the ValidationUtils class is used to reject the 'name' property 
		 * if it is null or the empty string.
		 * 
		 * (-) Have a look at the ValidationUtils javadocs to see what functionality it provides besides the example shown previously.
		 */
		ValidationUtils.rejectIfEmpty(e, "firstname", "name.empty");
		
		
		Person p = (Person) obj;
		if (p.getAge() < 0) {
			e.rejectValue("age", "negativevalue");
		} else if (p.getAge() > 110) {
			e.rejectValue("age", "too.darn.old");
		}
	}

}
