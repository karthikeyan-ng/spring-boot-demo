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
public class MainForInlineArrayConstruction {

	/**
	 * Array construction
	 * 		Arrays can be built using the familiar Java syntax, optionally supplying an initializer to 
	 * have the array populated at construction time.
	 * 
	 * It is not currently allowed to supply an initializer when constructing a multi-dimensional array.
	 */
	public static void main(String[] args) {
		
		EvaluationContext context = new StandardEvaluationContext();
		ExpressionParser parser = new SpelExpressionParser();

		int[] numbers1 = (int[]) parser.parseExpression("new int[4]").getValue(context);
		System.out.println(numbers1);

		// Array with initializer
		int[] numbers2 = (int[]) parser.parseExpression("new int[]{1,2,3}").getValue(context);
		System.out.println(numbers2);

		// Multi dimensional array
		int[][] numbers3 = (int[][]) parser.parseExpression("new int[4][5]").getValue(context);
		System.out.println(numbers3);
		

	}

}
