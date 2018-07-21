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
public class MainForSimpleStringExpression3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		ExpressionParser parser = new SpelExpressionParser();
		
		/**
		 * As an example of calling a JavaBean property, the String property Bytes can be called as shown below.
		 * 
		 * invokes 'getBytes()'
		 */
		Expression exp = parser.parseExpression("'Hello World'.bytes");
		byte[] bytes = (byte[]) exp.getValue();
		
		System.out.println(bytes.length);

	}

}
