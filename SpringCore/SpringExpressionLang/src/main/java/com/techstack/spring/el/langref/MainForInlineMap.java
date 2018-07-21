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
public class MainForInlineMap {

	/**
	 * Inline lists
	 * 		Lists can be expressed directly in an expression using {} notation.
	 * 		{} by itself means an empty list. For performance reasons, if the list is itself entirely composed of 
	 * fixed literals then a constant list is created to represent the expression, rather than building a 
	 * new list on each evaluation.
	 */
	public static void main(String[] args) {
		
		EvaluationContext context = new StandardEvaluationContext();
		ExpressionParser parser = new SpelExpressionParser();

		// evaluates to a Java list containing the four numbers
		List numbers = (List) parser.parseExpression("{1,2,3,4}").getValue(context); //List<Integers>
		System.out.println(numbers);

		List listOfLists = (List) parser.parseExpression("{{'a','b'},{'x','y'}}").getValue(context); //List<List<Integers>>
		System.out.println(listOfLists);
		

	}

}
