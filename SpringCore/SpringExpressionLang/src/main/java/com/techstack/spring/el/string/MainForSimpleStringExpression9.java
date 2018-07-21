/**
 * 
 */
package com.techstack.spring.el.string;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForSimpleStringExpression9 {

	/**
	 * SpEL Parser configuration example
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * - When specifying the compiler mode it is also possible to specify a classloader (passing null is allowed).
		 * - Compiled expressions will be defined in a child classloader created under any that is supplied.
		 * - It is important to ensure if a classloader is specified it can see all the types involved in the expression evaluation process.
		 * - If none is specified then a default classloader will be used (typically the context classloader for the thread that is 
		 * running during expression evaluation).
		 * 
		 * - Second approach
		 * 	- The second way to configure the compiler is for use when SpEL is embedded inside some other component
		 *  - it may not be possible to configure via a configuration object.
		 *  - In these cases it is possible to use a system property.
		 *  	- The property spring.expression.compiler.mode can be set to one of the SpelCompilerMode enum values (off, immediate, or mixed).
		 */
		SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE,
				this.getClass().getClassLoader());

		SpelExpressionParser parser = new SpelExpressionParser(config);

		Expression expr = parser.parseExpression("payload");

		MyMessage message = new MyMessage();

		Object payload = expr.getValue(message);

	}

}
