/**
 * 
 */
package com.techstack.spring._15.internationalization;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MyBean {

	@Autowired
	private MessageSource messageSource;

	public void doSomething() {
		// we are repeating 20 times with 2sec sleep, so that we can modify the
		// files outside (in target/classes folder) to watch the change
		// reloading.
		for (int i = 0; i < 20; i++) {
			System.out.println(messageSource.getMessage("message", new Object[] { "Karthikeyan" }, Locale.getDefault()));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
