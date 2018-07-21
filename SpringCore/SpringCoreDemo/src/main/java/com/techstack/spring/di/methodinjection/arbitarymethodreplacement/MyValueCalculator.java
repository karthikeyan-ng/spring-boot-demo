/**
 * 
 */
package com.techstack.spring.di.methodinjection.arbitarymethodreplacement;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MyValueCalculator {
	
	public String computeValue(String input) {

		input = input + "old implementation";
		
		return input;
    }

    // some other methods...

}
