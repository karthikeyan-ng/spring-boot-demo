/**
 * 
 */
package com.techstack.spring.el.string;

import java.util.GregorianCalendar;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.techstack.spring.el.beans.InventorSimple;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForSimpleStringExpression6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Create and set a calendar
		GregorianCalendar c = new GregorianCalendar();
		c.set(1856, 7, 9);
		
		//Step1: Prepare Inventor object
		InventorSimple tesla = new InventorSimple("Nikola Tesla", c.getTime(), "Serbian");
		
		//Step2: Create Spel Expression Parser
		ExpressionParser parser = new SpelExpressionParser();
		
		//Step3a: Parse expression
		Expression exp = parser.parseExpression("name");

		//Step3b: Access value from supplied object
		String name = (String) exp.getValue(tesla);
		
		System.out.println(name);

		//Step4a: expression contains boolean operator == to evaluate name propery value with the given value 
		exp = parser.parseExpression("name == 'Nikola Tesla'");
		boolean result = exp.getValue(tesla, Boolean.class);
		
		System.out.println(result);
	}

}
