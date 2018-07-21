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
public class MainForConstructors {

	/**
	 * Constructors
	 * 
	 * Constructors can be invoked using the new operator. The fully qualified class name should be used for all 
	 * but the primitive type and String (where int, float, etc, can be used).
	 * 
	 */
	public static void main(String[] args) {
		
		EvaluationContext context = new StandardEvaluationContext();
		ExpressionParser parser = new SpelExpressionParser();

		Inventor einstein = parser.parseExpression(
		        "new org.spring.samples.spel.inventor.Inventor('Albert Einstein', 'German')")
		        .getValue(Inventor.class);

		//create new inventor instance within add method of List
		parser.parseExpression(
		        "Members.add(new org.spring.samples.spel.inventor.Inventor('Albert Einstein', 'German'))")
		.getValue(societyContext);
		
	}
	
}
