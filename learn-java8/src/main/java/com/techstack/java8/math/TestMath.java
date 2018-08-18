/**
 * 
 */
package com.techstack.java8.math;

/**
 * The new methods added to one of the core classes of the language: java.lang.Math.
 * 
 * @author KARTHIKEYAN N
 *
 */
public class TestMath {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		evaluate_AllExactFunctions();
		evaluate_OtherNewFunctions();
	}

	/**
	 *  1. New *exact() Methods
	 * 		- addExact()
	 * 		- substractExact()
	 * 		- incrementExact()
	 * 		- decrementExact()
	 * 		- multiplyExact()
	 * 		- negateExact()
	 */
	private static void evaluate_AllExactFunctions() {
		
		/**
		 * addExact()
		 * 		Adds the two parameters, throwing an ArithmeticException in case of overflow
		 * (which goes for all *Exact() methods) of the addition:
		 */
//		Math.addExact(100, 50);               		// returns 150
//		Math.addExact(Integer.MAX_VALUE, 1);  		// throws ArithmeticException: integer overflow
//		Math.addExact(100000L, 150000L);	  		// returns 250000
//		Math.addExact(Long.MAX_VALUE, 1L);	  		// throws ArithmeticException: long overflow
		
		/**
		 * substractExact()
		 * 		Substracts the value of the second parameter from the first one, throwing an
		 * ArithmeticException in case of overflow of the subtraction:
		 */
//		Math.subtractExact(100, 50);           		// returns 50
//		Math.subtractExact(Integer.MIN_VALUE, 1); 	// throws ArithmeticException: integer overflow
//		Math.subtractExact(150000L, 100000L);		// returns 50000
//		Math.subtractExact(Long.MIN_VALUE, 1L);		// throws ArithmeticException: long overflow
		
		/**
		 * incrementExact()
		 * 		Increments the parameter by one, throwing an ArithmeticException in case of overflow:
		 */
//		Math.incrementExact(100);               	// returns 101
//		Math.incrementExact(Integer.MAX_VALUE); 	// throws ArithmeticException: integer overflow
//		Math.incrementExact(100000L);				// returns 100001
//		Math.incrementExact(Long.MAX_VALUE);		// throws ArithmeticException: long overflow
		
		/**
		 * decrementExact()
		 * 		Decrements the parameter by one, throwing an ArithmeticException in case of overflow:
		 */
//		Math.decrementExact(100);            		// returns 99
//		Math.decrementExact(Integer.MIN_VALUE); 	// throws ArithmeticException: integer overflow
//		Math.decrementExact(100000L);				// returns 99999
//		Math.decrementExact(Long.MIN_VALUE);		// throws ArithmeticException: long overflow
		
		/**
		 * multiplyExact()
		 * 		Multiply the two parameters, throwing an ArithmeticException in case of overflow of the product:
		 */
//		Math.multiplyExact(100, 5);            		// returns 500
//		Math.multiplyExact(Integer.MAX_VALUE, 2);	// throws ArithmeticException: integer overflow
//		Math.multiplyExact(100000L, 100000L);		// returns 10000000000
//		Math.multiplyExact(Long.MAX_VALUE, 2); 		// throws ArithmeticException: long overflow
		
		/**
		 * negateExact() 
		 * 	Changes the sign of the parameter, throwing an
		 * ArithmeticException in case of overflow.
		 * 
		 * In this case, we have to think about the internal representation of the value
		 * in memory to understand why there’s an overflow, as is not as intuitive as
		 * the rest of the “exact” methods:
		 */
		Math.negateExact(100);               		// returns -100
		/**
		 * The overflow is due to the Integer.MIN_VALUE being −2.147.483.648, and on the
		 * other side the Integer.MAX_VALUE being 2.147.483.647 so the returned value
		 * doesn’t fit into an Integer by one unit.
		 */
		Math.negateExact(Integer.MIN_VALUE); 		// throws ArithmeticException: integer overflow
		Math.negateExact(100000L);					// returns -100000
		Math.negateExact(Long.MIN_VALUE);			// throws ArithmeticException: long overflow
	}
	
	/**
	 * Other newly added functions in Java8
	 * 	- floorDiv()
	 * 	- modDiv()
	 * 	- nextDown()
	 */
	private static void evaluate_OtherNewFunctions() {
		
		/**
		 * floorDiv() 
		 * 		- Divides the first parameter by the second one, and then performs
		 * a floor() operation over the result, returning the Integer that is less or
		 * equal to the quotient:
		 */
		Math.floorDiv(7, 2);  			// returns 3	//The exact quotient is 3.5 so floor(3.5) == 3.
		Math.floorDiv(-7, 2); 			// returns -4	//The exact quotient is -3.5 so floor(-3.5) == -4.
		
		/**
		 * modDiv() 
		 * 		- This one is similar to the previous method floorDiv(), but
		 * applying the floor() operation over the modulus or remainder of the division
		 * instead of the quotient:
		 * 
		 * As we can see, the modDiv() for two positive numbers is the same as % operator.
		 */
//		Math.modDiv(5, 3);  			// returns 2
//		Math.modDiv(-5, 3);  			// returns 1	//It returns 1 and not 2 because floorDiv(-5, 3) is -2 and not -1.
		
		/**
		 * nextDown()
		 * 		Returns the immediately lower value of the parameter (supports float or double parameters):
		 */
		float f = Math.nextDown(3);  // returns 2.9999998
		double d = Math.nextDown(3); // returns 2.999999761581421
	}
}
