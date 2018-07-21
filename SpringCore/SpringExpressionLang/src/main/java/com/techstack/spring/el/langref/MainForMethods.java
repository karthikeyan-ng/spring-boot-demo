/**
 * 
 */
package com.techstack.spring.el.langref;

import java.util.List;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForMethods {

	/**
	 * Methods are invoked using typical Java programming syntax. You may also invoke methods on literals. 
	 * Varargs are also supported.
	 */
	public static void main(String[] args) {
		
		EvaluationContext context = new StandardEvaluationContext();
		ExpressionParser parser = new SpelExpressionParser();

		// string literal, evaluates to "bc"
		String bc = parser.parseExpression("'abc'.substring(1, 3)").getValue(String.class);
		System.out.println(bc);

		// evaluates to true
//		boolean isMember = parser.parseExpression("isMember('Mihajlo Pupin')").getValue(
//		        societyContext, Boolean.class);

	}

}
