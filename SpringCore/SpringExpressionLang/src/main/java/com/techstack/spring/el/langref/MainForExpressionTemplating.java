/**
 * 
 */
package com.techstack.spring.el.langref;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForExpressionTemplating {

	/**
	 * Expression templating
	 * 
	 * Expression templates allow a mixing of literal text with one or more evaluation blocks. 
	 * Each evaluation block is delimited with prefix and suffix characters that you can define, 
	 * a common choice is to use #{ } as the delimiters.
	 * 
	 */
	public static void main(String[] args) {

		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext(); // SimpleEvaluationContext.forReadOnlyDataBinding().build();
	
		String randomPhrase = parser.parseExpression(
		        "random number is #{T(java.lang.Math).random()}",
		        new TemplateParserContext()).getValue(String.class);

		// evaluates to "random number is 0.7038186818312008"
		System.out.println(randomPhrase);
	}
	
	
	/**
	 * The string is evaluated by concatenating the literal text 'random number is ' with the result of evaluating the expression 
	 * inside the #{ } delimiter, in this case the result of calling that random() method. 
	 * 
	 * The second argument to the method parseExpression() is of the type ParserContext. 
	 * 
	 * The ParserContext interface is used to influence how the expression is parsed in order to support the expression 
	 * templating functionality. 
	 * 
	 * The definition of TemplateParserContext is shown below.
	 * 
	 * <code>
	 * 			public class TemplateParserContext implements ParserContext {

				    public String getExpressionPrefix() {
				        return "#{";
				    }
				
				    public String getExpressionSuffix() {
				        return "}";
				    }
				
				    public boolean isTemplate() {
				        return true;
				    }
				}
	 * </code>
	 */
		
}
