/**
 * 
 */
package com.techstack.spring.el.langref;

import java.util.List;
import java.util.Map;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.techstack.spring.el.beans.Inventor;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForCollectionProjection {

	/**
	 * Collection Projection
	 * 
	 * Projection allows a collection to drive the evaluation of a sub-expression and the result is a new collection. 
	 * 
	 * The syntax for projection is .![projectionExpression]. 
	 * 
	 */
	public static void main(String[] args) {

		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext(); // SimpleEvaluationContext.forReadOnlyDataBinding().build();
		
		/**
		 * Most easily understood by example, suppose we have a list of inventors but want the list of cities where they were born. 
		 * Effectively we want to evaluate 'placeOfBirth.city' for every entry in the inventor list. Using projection:
		 */
		// returns ['Smiljan', 'Idvor' ]
		List placesOfBirth = (List) parser.parseExpression("Members.![placeOfBirth.city]");
		
		/**
		 * A map can also be used to drive projection and in this case the projection expression is evaluated against each entry 
		 * in the map (represented as a Java Map.Entry). The result of a projection across a map is a list consisting of the 
		 * evaluation of the projection expression against each map entry.
		 */
	}
	
}
