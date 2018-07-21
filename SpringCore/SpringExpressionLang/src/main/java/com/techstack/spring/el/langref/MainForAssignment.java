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
public class MainForAssignment {

	/**
	 * Assignment Operator
	 * 
	 * Setting of a property is done by using the assignment operator. This would typically be done 
	 * within a call to setValue but can also be done inside a call to getValue.
	 */
	public static void main(String[] args) {
		
		EvaluationContext context = new StandardEvaluationContext();
		ExpressionParser parser = new SpelExpressionParser();

		Inventor inventor = new Inventor();
		EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();

		parser.parseExpression("Name").setValue(context, inventor, "Aleksandar Seovic");

		// alternatively
		String aleks = parser.parseExpression(
		        "Name = 'Aleksandar Seovic'").getValue(context, inventor, String.class);
		
		
	}
	
}
