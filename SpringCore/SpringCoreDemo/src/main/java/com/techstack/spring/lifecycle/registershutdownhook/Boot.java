/**
 * 
 */
package com.techstack.spring.lifecycle.registershutdownhook;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author KARTHIKEYAN N
 *
 */
public final class Boot {

	public static void main(final String[] args) throws Exception {
		
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

		// add a shutdown hook for the above context...
		ctx.registerShutdownHook();

		// app runs here...

		// main method exits, hook is called prior to the app shutting down...
	}
}
