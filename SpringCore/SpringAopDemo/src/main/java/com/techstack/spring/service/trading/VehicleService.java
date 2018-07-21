package com.techstack.spring.service.trading;

import org.springframework.stereotype.Component;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Component
public class VehicleService {
	
	private int i=1;
	
	public void getVehicleInfo() {
		System.out.println("Vehicle is Fore Ecosport, i=" + i);
	}
	
	public void getVehiclePriceInfo() {
		i++;
		System.out.println("Vehicle price is Rs.1200000");
	}

}
