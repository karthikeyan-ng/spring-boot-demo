/**
 * 
 */
package com.techstack.spring.el.langref;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.techstack.spring.el.beans.Inventor;
import com.techstack.spring.el.beans.PlaceOfBirth;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForSafeNavigationOperator {

	/**
	 * Safe Navigation operator
	 * 
	 * The Safe Navigation operator is used to avoid a NullPointerException and comes from the Groovy language. 
	 * Typically when you have a reference to an object you might need to verify that it is not null before accessing methods 
	 * or properties of the object. 
	 * 
	 * To avoid this, the safe navigation operator will simply return null instead of throwing an exception.
	 * 
	 */
	public static void main(String[] args) {

		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext(); // SimpleEvaluationContext.forReadOnlyDataBinding().build();

		Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
		tesla.setPlaceOfBirth(new PlaceOfBirth("Smiljan"));

		String city = parser.parseExpression("PlaceOfBirth?.City").getValue(context, tesla, String.class);
		System.out.println(city);  // Smiljan

		tesla.setPlaceOfBirth(null);
		city = parser.parseExpression("PlaceOfBirth?.City").getValue(context, tesla, String.class);
		System.out.println(city);  // null - does not throw NullPointerException!!!
		
	}
	
}
