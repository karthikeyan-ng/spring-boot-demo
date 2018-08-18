/**
 * 
 */
package com.techstack.java8.interfacee;

/**
 * @author KARTHIKEYAN N
 *
 */
public interface Vehicle {
	
	/**
	 * Sample static method in Java8 Interface.
	 * 
	 * The static producer() method is available only through and inside of an interface. 
	 * It can’t be overridden by an implementing class.
	 * 
	 * @return
	 */
	static String producer() {
	    return "Ford";
	}
	
	/**
	 * Default methods are declared using the new default keyword. These are accessible through the 
	 * instance of the implementing class and can be overridden.
	 * 
	 * @return
	 */
	default String getOverview() {
	    return "ATV made by " + producer();
	}
	
	

}
