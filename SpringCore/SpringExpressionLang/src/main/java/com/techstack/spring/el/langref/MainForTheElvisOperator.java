/**
 * 
 */
package com.techstack.spring.el.langref;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.techstack.spring.el.beans.Inventor;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForTheElvisOperator {

	/**
	 * The Elvis Operator
	 * 
	 * The Elvis operator is a shortening of the ternary operator syntax and is used in the Groovy language. 
	 * With the ternary operator syntax you usually have to repeat a variable twice, for example:
	 * 
	 * Usual ternary operator...
	 * <code>
	 * 		String name = "Elvis Presley";
     *		String displayName = (name != null ? name : "Unknown");
	 * </code>
	 * 
	 * TIP:
	 * The Elvis operator can be used to apply default values in expressions, e.g. in an @Value expression:
	 * 
	 * <code>
	 * 		@Value("#{systemProperties['pop3.port'] ?: 25}")
	 * </code>
	 * This will inject a system property pop3.port if it is defined or 25 if not.
	 * 
	 */
	public static void main(String[] args) {

		//Instead you can use the Elvis operator, named for the resemblance to Elvis' hair style.
		ExpressionParser parser = new SpelExpressionParser();

		String name = parser.parseExpression("name?:'Unknown'").getValue(String.class);
		System.out.println(name);  // 'Unknown'
		
		
		//Here is a more complex example.
		EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

		Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
		name = parser.parseExpression("Name?:'Elvis Presley'").getValue(context, tesla, String.class);
		System.out.println(name);  // Nikola Tesla

		tesla.setName(null);
		name = parser.parseExpression("Name?:'Elvis Presley'").getValue(context, tesla, String.class);
		System.out.println(name);  // Elvis Presley
		
	}
	
}
