package com.techstack.spring.validator.complex;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Component(value = "addressValidator")
public class AddressValidator implements Validator {

	/**
	 * This Validator validates *just* Address instances
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Address.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "streetName", "address street name is empty");
		ValidationUtils.rejectIfEmpty(e, "city", "address city is empty");
		Address p = (Address) obj;
		if (p.getZipcode() < 0) {
			e.rejectValue("zipcode", "negativevalue");
		} else if (!(p.getZipcode() >= 600 && p.getZipcode() <= 800)) {
			e.rejectValue("zipcode", "not in range is not between 600 and 800");
		}
	}

}
