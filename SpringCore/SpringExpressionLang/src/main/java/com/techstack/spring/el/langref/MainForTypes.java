/**
 * 
 */
package com.techstack.spring.el.langref;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.techstack.spring.el.beans.Inventor;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForTypes {

	/**
	 * Types
	 * 
	 * The special T operator can be used to specify an instance of java.lang.Class (the type). 
	 * 
	 * Static methods are invoked using this operator as well. 
	 * 
	 * The StandardEvaluationContext uses a TypeLocator to find types and the StandardTypeLocator (which can be replaced) 
	 * is built with an understanding of the java.lang package. 
	 * 
	 * This means T() references to types within java.lang do not need to be fully qualified, but all other type 
	 * references must be.
	 * 
	 * 
	 */
	public static void main(String[] args) {
		
		EvaluationContext context = new StandardEvaluationContext();
		ExpressionParser parser = new SpelExpressionParser();

		Class dateClass = parser.parseExpression("T(java.util.Date)").getValue(Class.class);

		Class stringClass = parser.parseExpression("T(String)").getValue(Class.class);

		boolean trueValue = parser.parseExpression(
		        "T(java.math.RoundingMode).CEILING < T(java.math.RoundingMode).FLOOR")
		        .getValue(Boolean.class);
		System.out.println(trueValue);
		
	}
	
}
