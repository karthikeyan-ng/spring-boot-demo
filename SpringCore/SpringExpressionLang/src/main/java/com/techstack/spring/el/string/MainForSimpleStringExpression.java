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
public class MainForSimpleStringExpression {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		/**
		 * - The interface ExpressionParser is responsible for parsing an expression string.
		 * - In this example the expression string is a string literal denoted by the surrounding single quotes.
		 * - 
		 */
		ExpressionParser parser = new SpelExpressionParser();
		
		/**
		 * - The interface Expression is responsible for evaluating the given expression string ('Hello World').
		 * - There are two exceptions that can be thrown, 
		 * 		- ParseException and EvaluationException when calling parser.parseExpression and exp.getValue respectively.
		 * 
		 * Here, Hello World is been given inside a single quote('). If you remove this, Spring SpEL don't know
		 * how to parse this expression.
		 * 
		 * Spring would throw this exception
		 * org.springframework.expression.spel.SpelParseException: EL1041E: After parsing a valid expression, there is still more 
		 * data in the expression: 'World'
		 * 
		 * Which means, Spring will treat each word a unique expression identifier.
		 */
		Expression exp = parser.parseExpression("'Hello World'");
		String message = (String) exp.getValue();
		
		System.out.println(message);

	}

}
