/**
 * 
 */
package com.techstack.spring._13.environmentabstraction.property;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author KARTHIKEYAN N
 *
 */
public class CodeSnippet1 {

	public static void main(String[] args) {

		ApplicationContext ctx = new GenericApplicationContext();
		Environment env = ctx.getEnvironment();
		boolean containsFoo = env.containsProperty("foo");
		System.out.println("Does my environment contain the 'foo' property? " + containsFoo);
	}

}
/**
 * In the snippet above, we see a high-level way of asking Spring whether the foo property is defined for the current environment. 
 * To answer this question, the Environment object performs a search over a set of PropertySource objects. 
 * A PropertySource is a simple abstraction over any source of key-value pairs, and Spring’s StandardEnvironment is configured with 
 * two PropertySource objects — one representing the set of JVM system properties (a la System.getProperties()) and 
 * one representing the set of system environment variables (a la System.getenv()). 
 * 
 */
