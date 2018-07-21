/**
 * 
 */
package com.techstack.spring.el.langref;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.techstack.spring.el.beans.MyBeanResolver;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForTernaryOperator {

	/**
	 * Ternary Operator (If-Then-Else)
	 * 
	 */
	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();
		
		//You can use the ternary operator for performing if-then-else conditional logic inside the expression. A minimal example is:
		String falseString = parser.parseExpression(
		        "false ? 'trueExp' : 'falseExp'").getValue(String.class); //the boolean false results in returning the string value 'falseExp'.
		
		
		//realistic example is shown below.
		parser.parseExpression("Name").setValue(societyContext, "IEEE");
		societyContext.setVariable("queryName", "Nikola Tesla");

		expression = "isMember(#queryName)? #queryName + ' is a member of the ' " +
		        "+ Name + ' Society' : #queryName + ' is not a member of the ' + Name + ' Society'";

		String queryResultString = parser.parseExpression(expression)
		        .getValue(societyContext, String.class);
		// queryResultString = "Nikola Tesla is a member of the IEEE Society"
		
	}
	
}
