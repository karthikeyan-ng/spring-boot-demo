/**
 * 
 */
package com.techstack.spring.el.string;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.techstack.spring.el.beans.Simple;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForSimpleStringExpression7 {

	/**
	 * SpEL Type conversion example
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Simple simple = new Simple();
		simple.booleanList.add(true);
		
		EvaluationContext context = SimpleEvaluationContext().forReadOnlyDataBinding().build();
		
		//EvaluationContext context = new StandardEvaluationContext().forReadOnlyDataBinding().build();
		
		ExpressionParser parser = new SpelExpressionParser();
		
		// false is passed in here as a string. SpEL and the conversion service will
		// correctly recognize that it needs to be a Boolean and convert it
		parser.parseExpression("booleanList[0]").setValue(context, simple, "false");

		// b will be false
		Boolean b = simple.booleanList.get(0);
		
	}

}
