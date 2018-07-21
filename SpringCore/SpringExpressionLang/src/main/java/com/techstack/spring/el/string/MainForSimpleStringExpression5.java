/**
 * 
 */
package com.techstack.spring.el.string;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForSimpleStringExpression5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		ExpressionParser parser = new SpelExpressionParser();
		
		/**
		 * The String’s constructor can be called instead of using a string literal.
		 * 
		 */
		Expression exp = parser.parseExpression("new String('hello world').toUpperCase()");

		/**
		 * - Note the use of the generic method public <T> T getValue(Class<T> desiredResultType).
		 * - Using this method removes the need to cast the value of the expression to the desired result type.
		 * - An EvaluationException will be thrown if the value cannot be cast to the type T or converted using the registered type converter.
		 */
		String message = exp.getValue(String.class);
		
		System.out.println(message);

	}

}
