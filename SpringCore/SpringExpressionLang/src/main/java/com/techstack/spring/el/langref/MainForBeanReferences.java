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
public class MainForBeanReferences {

	/**
	 * Bean references
	 * 
	 */
	public static void main(String[] args) {
		
		simpleBeanReferences();
		
	}
	
	/**
	 * If the evaluation context has been configured with a bean resolver it is possible to 
	 * look up beans from an expression using the @ symbol.
	 */
	public static void simpleBeanReferences() {
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setBeanResolver(new MyBeanResolver());

		// This will end up calling resolve(context,"foo") on MyBeanResolver during evaluation
		Object bean = parser.parseExpression("@foo").getValue(context);
	}
	
	/**
	 * To access a factory bean itself, the bean name should instead be prefixed with an & symbol.
	 */
	public static void beanReferencesUsingFactoryMethod() {
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setBeanResolver(new MyBeanResolver());

		// This will end up calling resolve(context,"&foo") on MyBeanResolver during evaluation
		Object bean = parser.parseExpression("&foo").getValue(context);
	}
	
}
