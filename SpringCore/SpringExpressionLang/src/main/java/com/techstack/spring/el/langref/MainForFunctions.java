/**
 * 
 */
package com.techstack.spring.el.langref;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.techstack.spring.el.beans.Inventor;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForFunctions {

	/**
	 * Functions
	 * 
	 * You can extend SpEL by registering user defined functions that can be called within the 
	 * expression string. The function is registered through the EvaluationContext.
	 * 
	 * Method method = ...;
	 * EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
	 * context.setVariable("myFunction", method);
	 * 
	 */
	public static void main(String[] args) {
		
		ExpressionParser parser = new SpelExpressionParser();

		EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
		context.setVariable("reverseString",
		        StringUtils.class.getDeclaredMethod("reverseString", String.class));

		String helloWorldReversed = parser.parseExpression(
		        "#reverseString('hello')").getValue(context, String.class);
		
	}
	
}
