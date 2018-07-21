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
public class MainForSimpleStringExpression4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		ExpressionParser parser = new SpelExpressionParser();
		
		/**
		 * SpEL also supports nested properties using standard dot notation, i.e. prop1.prop2.prop3 and the setting of property values
		 * 
		 * Public fields may also be accessed.
		 * 
		 * invokes 'getBytes().length'
		 * 
		 */
		Expression exp = parser.parseExpression("'Hello World'.bytes.length");
		int length = (Integer) exp.getValue();
		
		System.out.println(length);

	}

}
