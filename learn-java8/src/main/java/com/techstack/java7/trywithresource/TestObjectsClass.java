/**
 * 
 */
package com.techstack.java7.trywithresource;

import java.util.Objects;

/**
 * Java 7 has come up with a new class Objects that have 9 static utility
 * methods for operating on objects. These utilities include null-safe methods
 * for computing the hash code of an object, returning a string for an object,
 * and comparing two objects.
 * 
 * Using Objects class methods, one can smartly handle NullPointerException and
 * can also show customized NullPointerException message(if an Exception occur).
 * 
 * @author KARTHIKEYAN N
 *
 */
public class TestObjectsClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String str = null;
		
		System.out.println(Objects.isNull(str));	//(1) true
		System.out.println(Objects.nonNull(str));	//(2) false
		
		Vehicle ford = new Vehicle(null, "Ford");
		System.out.println(ford.toString());
		
		Vehicle audi = new Vehicle(null, "Audi");
		Vehicle ford1 = new Vehicle(null, "Ford");
		System.out.println("Is both Vehicle objects are equal? " + Objects.equals(ford, audi));	//==>(8)
		System.out.println("Is both Vehicle objects are equal? " + Objects.equals(ford, ford1));
		
		System.out.println("Hash for supplied objects : " + Objects.hash(ford, ford1, audi));	//==>(9)
		System.out.println("Get the HashCode for the supplied Object : " + Objects.hashCode(ford));	//==>(10)
		
		// boolean deepEquals(Object a, Object b) : Almost the same as the first method (Objects.equals)
		// except that if both a and b are arrays, the equality is evaluated using
		// Arrays.deepEquals method.
			// Objects.deepEquals	==> (11)
		
		// This method returns 0 if a == b or if both are null otherwise c.compare(a,
		// b). The support of null is delegated to the comparator.
			// Objects.compare		==> (12)
	}
}

class Vehicle {
	
	private Integer code;
	private String type;
	public Vehicle(Integer code, String type) {
		//this.code = Objects.requireNonNull(code);	//(3)==> NPE
		//this.code = Objects.requireNonNull(code, "Vehicle code is must!");	//(4)==>NPE with message
		//this.code = Objects.requireNonNull(code, Vehicle::createMessage);	//(5)==>Java8, Functional style
		this.code = code;
		this.type = type;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getType() {
		return type;
	}
	
	private static String createMessage() {
        return "Vehicle code is must!";
    }
	
	@Override
	public boolean equals(Object other) {
		if ((other != null) && (other instanceof Vehicle)) {
			return ((Vehicle) other).getType() == getType();
		} else {
			return super.equals(other);
		}
	}
	
	@Override
	public int hashCode() {
		return 23 * 3 * (this.code == null ? 1 : this.code.hashCode())
				+ 23 * 7 * (this.type == null ? 1 : this.type.hashCode());
	}
	
	@Override
	public String toString() {
		//return Objects.toString(code) +  ", " + type;	//==>(6)
		return Objects.toString(code, "Vehicle code is null") +  ", " + type;	//==>(7)
	}
}
