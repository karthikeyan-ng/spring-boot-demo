/**
 * 
 */
package com.techstack.spring.el.langref;

/**
 * @author KARTHIKEYAN N
 *
 */
public abstract class StringUtils {

	/**
	 * given a utility method to reverse a string is shown below:
	 * 
	 * @param input
	 * @return
	 */
	public static String reverseString(String input) {
		StringBuilder backwards = new StringBuilder(input.length());
		for (int i = 0; i < input.length(); i++) {
			backwards.append(input.charAt(input.length() - 1 - i));
		}
		return backwards.toString();
	}
}
