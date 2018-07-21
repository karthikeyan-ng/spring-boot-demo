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
public class MainForOperators {

	/**
	 * Methods are invoked using typical Java programming syntax. You may also invoke methods on literals. 
	 * Varargs are also supported.
	 */
	public static void main(String[] args) {
		
		EvaluationContext context = new StandardEvaluationContext();
		ExpressionParser parser = new SpelExpressionParser();

		relationalOperators(parser);
		otherSpelSupportingOperators(parser);
		relationalOperatorsAlphabeticEquivalent(parser);
		
		
	}
	
	/**
	 * Relational operators
	 * 
	 * The relational operators; 
	 * 	equal, 
	 * 	not equal, 
	 * 	less than, 
	 * 	less than or equal, 
	 * 	greater than, and 
	 * 	greater than or 
	 * 	equal 
	 * are supported using standard operator notation.
	 * 
	 * TIP:
	 * 		Greater/less-than comparisons against null follow a simple rule: null is treated as nothing here (i.e. NOT as zero). 
	 * As a consequence, any other value is always greater than null (X > null is always true) and no other value is ever 
	 * less than nothing (X < null is always false).
	 *
	 * If you prefer numeric comparisons instead, please avoid number-based null comparisons in favor of 
	 * comparisons against zero (e.g. X > 0 or X < 0).
	 */
	public static void relationalOperators(ExpressionParser parser) {
		// evaluates to true
		boolean trueValue = parser.parseExpression("2 == 2").getValue(Boolean.class);
		System.out.println(trueValue);

		// evaluates to false
		boolean falseValue = parser.parseExpression("2 < -5.0").getValue(Boolean.class);
		System.out.println(falseValue);

		// evaluates to true
		boolean trueValue1 = parser.parseExpression("'black' < 'block'").getValue(Boolean.class);
		System.out.println(trueValue1);
	}

	/**
	 * In addition to standard relational operators SpEL supports the instanceof and 
	 * regular expression based matches operator.
	 * 
	 * TIP:
	 * Be careful with primitive types as they are immediately boxed up to the wrapper type, 
	 * so 1 instanceof T(int) evaluates to false while 1 instanceof T(Integer) evaluates to true, as expected.
	 * 
	 * @param parser
	 */
	public static void otherSpelSupportingOperators(ExpressionParser parser) {
		// evaluates to false
		boolean falseValue = parser.parseExpression(
		        "'xyz' instanceof T(Integer)").getValue(Boolean.class);
		System.out.println(falseValue);

		// evaluates to true
		boolean trueValue = parser.parseExpression(
		        "'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
		System.out.println(trueValue);

		//evaluates to false
		boolean falseValue1 = parser.parseExpression(
		        "'5.0067' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
		System.out.println(falseValue1);
	}
	
	/**
	 * Each symbolic operator can also be specified as a purely alphabetic equivalent. This avoids problems 
	 * where the symbols used have special meaning for the document type in which the expression is embedded 
	 * (eg. an XML document). The textual equivalents are shown here: 
	 * lt (<), gt (>), le (<=), ge (>=), eq (==), ne (!=), div (/), mod (%), not (!). 
	 * These are case insensitive.
	 */
	public static void relationalOperatorsAlphabeticEquivalent(ExpressionParser parser) {
		
		// evaluates to true
		boolean trueValue = parser.parseExpression("2 eq 2").getValue(Boolean.class);
		System.out.println(trueValue);

		// evaluates to false
		boolean falseValue = parser.parseExpression("2 lt -5.0").getValue(Boolean.class);
		System.out.println(falseValue);

		// evaluates to true
		boolean trueValue1 = parser.parseExpression("'black' lt 'block'").getValue(Boolean.class);
		System.out.println(trueValue1);
	}
	
	/**
	 * Logical operators
	 * 
	 * The logical operators that are supported are and, or, and not. Their use is demonstrated below.
	 * 
	 * @param parser
	 */
	public static void logicalOperators(ExpressionParser parser) {
		
		// -- AND --

		// evaluates to false
		boolean falseValue = parser.parseExpression("true and false").getValue(Boolean.class);
		System.out.println(falseValue);

		// evaluates to true
		String expression = "isMember('Nikola Tesla') and isMember('Mihajlo Pupin')";
		//boolean trueValue = parser.parseExpression(expression).getValue(societyContext, Boolean.class);

		// -- OR --

		// evaluates to true
		boolean trueValue = parser.parseExpression("true or false").getValue(Boolean.class);
		System.out.println(trueValue);

		// evaluates to true
		expression = "isMember('Nikola Tesla') or isMember('Albert Einstein')";
		//boolean trueValue = parser.parseExpression(expression).getValue(societyContext, Boolean.class);

		// -- NOT --

		// evaluates to false
		boolean falseValue1 = parser.parseExpression("!true").getValue(Boolean.class);
		System.out.println(falseValue1);

		// -- AND and NOT --
		expression = "isMember('Nikola Tesla') and !isMember('Mihajlo Pupin')";
		//boolean falseValue = parser.parseExpression(expression).getValue(societyContext, Boolean.class);
	}
	
	/**
	 * Mathematical operators
	 * 
	 * The addition operator can be used on both numbers and strings. 
	 * Subtraction, multiplication and division can be used only on numbers. 
	 * Other mathematical operators supported are modulus (%) and exponential power (^). 
	 * Standard operator precedence is enforced. 
	 * These operators are demonstrated below.
	 * 
	 * @param parser
	 */
	public static void mathematicalOperators(ExpressionParser parser) {
		
		// Addition
		int two = parser.parseExpression("1 + 1").getValue(Integer.class);  // 2

		String testString = parser.parseExpression(
		        "'test' + ' ' + 'string'").getValue(String.class);  // 'test string'

		// Subtraction
		int four = parser.parseExpression("1 - -3").getValue(Integer.class);  // 4

		double d = parser.parseExpression("1000.00 - 1e4").getValue(Double.class);  // -9000

		// Multiplication
		int six = parser.parseExpression("-2 * -3").getValue(Integer.class);  // 6

		double twentyFour = parser.parseExpression("2.0 * 3e0 * 4").getValue(Double.class);  // 24.0

		// Division
		int minusTwo = parser.parseExpression("6 / -3").getValue(Integer.class);  // -2

		double one = parser.parseExpression("8.0 / 4e0 / 2").getValue(Double.class);  // 1.0

		// Modulus
		int three = parser.parseExpression("7 % 4").getValue(Integer.class);  // 3

		int one1 = parser.parseExpression("8 / 5 % 2").getValue(Integer.class);  // 1

		// Operator precedence
		int minusTwentyOne = parser.parseExpression("1+2-3*8").getValue(Integer.class);  // -21
	}
}
