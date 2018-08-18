/**
 * 
 */
package com.techstack.java8.interfacee;

/**
 * @author KARTHIKEYAN N
 *
 */
public class TestJava8Interface {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// To call it outside the interface the standard approach for static method call should be used:
		String producer = Vehicle.producer();
		System.out.println(producer);
		
		/**
		 * Assume that this interface is implemented by the class VehicleImpl. For executing 
		 * the default method an instance of this class should be created:
		 */
		Vehicle vehicle = new VehicleImpl();
		String overview = vehicle.getOverview();
		System.out.println(overview);

	}

}
