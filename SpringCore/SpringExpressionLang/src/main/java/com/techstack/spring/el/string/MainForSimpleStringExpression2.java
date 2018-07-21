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
public class MainForSimpleStringExpression2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		ExpressionParser parser = new SpelExpressionParser();
		
		/**
		 * Here, in this example, we have demonstrated how to call the concat method on the string literal.
		 */
		Expression exp = parser.parseExpression("'Hello World'.concat('!')");
		String message = (String) exp.getValue();
		
		System.out.println(message);

	}

}
