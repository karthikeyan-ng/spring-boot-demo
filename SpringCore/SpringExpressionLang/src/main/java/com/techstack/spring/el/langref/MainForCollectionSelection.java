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
public class MainForCollectionSelection {

	/**
	 * Collection Selection
	 * 
	 * Selection is a powerful expression language feature that allows you to transform some source collection into another by 
	 * selecting from its entries.
	 * 
	 */
	public static void main(String[] args) {

		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext(); // SimpleEvaluationContext.forReadOnlyDataBinding().build();

		/**
		 * Selection uses the syntax .?[selectionExpression]. This will filter the collection and return a new collection containing 
		 * a subset of the original elements. For example, selection would allow us to easily get a list of Serbian inventors:
		 * 
		 * Selection is possible upon both lists and maps.
		 * 
		 * 	- In the former case the selection criteria is evaluated against each individual list element
		 *  - a map the selection criteria is evaluated against each map entry (objects of the Java type Map.Entry). 
		 */
		List<Inventor> list = (List<Inventor>) parser.parseExpression(
		        "Members.?[Nationality == 'Serbian']").getValue(societyContext);
		
		
		/**
		 * This expression will return a new map consisting of those elements of the original map where the entry value is less than 27.
		 */
		Map newMap = parser.parseExpression("map.?[value<27]").getValue(Map.class);
		
		/**
		 * NOTE:
		 * 	- In addition to returning all the selected elements, it is possible to retrieve just the first or the last value.
		 * 	- To obtain the first entry matching the selection the syntax is .^[selectionExpression]
		 *  - To obtain the last matching selection the syntax is .$[selectionExpression].
		 */
	}
	
}
