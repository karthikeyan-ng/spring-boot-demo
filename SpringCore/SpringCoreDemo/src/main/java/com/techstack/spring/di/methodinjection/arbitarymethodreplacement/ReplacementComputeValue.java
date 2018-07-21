/**
 * 
 */
package com.techstack.spring.di.methodinjection.arbitarymethodreplacement;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

/**
 * @author KARTHIKEYAN N
 *
 */
public class ReplacementComputeValue implements MethodReplacer {

	@Override
	public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
		// get the input value, work with it, and return a computed result
        String input = (String) args[0];
        input = input + " added new implementation";
        return input;
	}

}
