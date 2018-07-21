/**
 * 
 */
package com.techstack.spring.el.langref;

import java.util.Map;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForInlineLists {

	/**
	 * Inline Maps
	 * 		Maps can also be expressed directly in an expression using {key:value} notation.
	 * 
	 * 		{:} by itself means an empty map. For performance reasons, if the map is itself composed of fixed 
	 * literals or other nested constant structures (lists or maps) then a constant map is created to represent 
	 * the expression, rather than building a new map on each evaluation. Quoting of the map keys is optional, 
	 * the examples below are not using quoted keys.
	 */
	public static void main(String[] args) {
		
		EvaluationContext context = new StandardEvaluationContext();
		ExpressionParser parser = new SpelExpressionParser();

		// evaluates to a Java map containing the two entries
		//Map<String, String>
		Map<String, String> inventorInfo = (Map<String, String>) 
				parser.parseExpression("{name:'Nikola',dob:'10-July-1856'}").getValue(context);
		System.out.println(inventorInfo);
		System.out.println(inventorInfo.size());

		//Map<String, Map<String, String>>
		Map mapOfMaps = (Map) parser.parseExpression("{name:{first:'Nikola',last:'Tesla'},dob:{day:10,month:'July',year:1856}}").getValue(context);
		System.out.println(mapOfMaps);

	}

}
