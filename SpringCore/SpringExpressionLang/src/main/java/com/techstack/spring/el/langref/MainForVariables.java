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
public class MainForVariables {
	
	/**
	 * Variables
	 * 
	 * Variables can be referenced in the expression using the syntax #variableName. 
	 * Variables are set using the method setVariable on EvaluationContext implementations.
	 * 
	 */
	public static void main(String[] args) {
		
		ExpressionParser parser = new SpelExpressionParser();

		Inventor tesla = new Inventor("Nikola Tesla", "Serbian");

		EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();
		context.setVariable("newName", "Mike Tesla");

		parser.parseExpression("Name = #newName").getValue(context, tesla);
		System.out.println(tesla.getName());  // "Mike Tesla"
		
	}
	
	/**
	 * The #this and #root variables
	 * 
	 * The variable #this is always defined and refers to the current evaluation object 
	 * (against which unqualified references are resolved). 
	 * 
	 * The variable #root is always defined and refers to the root context object. Although #this may 
	 * vary as components of an expression are evaluated, #root always refers to the root.
	 * 
	 */
	public static void thisAndRootVariable() {
		// create an array of integers
		List<Integer> primes = new ArrayList<Integer>();
		primes.addAll(Arrays.asList(2,3,5,7,11,13,17));

		// create parser and set variable 'primes' as the array of integers
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataAccess();
		context.setVariable("primes", primes);

		// all prime numbers > 10 from the list (using selection ?{...})
		// evaluates to [11, 13, 17]
		List<Integer> primesGreaterThanTen = (List<Integer>) parser.parseExpression(
		        "#primes.?[#this>10]").getValue(context);
	}

}
