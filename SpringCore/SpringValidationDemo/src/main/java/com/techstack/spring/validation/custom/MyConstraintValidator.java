/**
 * 
 */
package com.techstack.spring.validation.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MyConstraintValidator implements ConstraintValidator {

	@Autowired
    private Foo aDependency;

	@Override
	public boolean isValid(Object arg0, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	//...
}