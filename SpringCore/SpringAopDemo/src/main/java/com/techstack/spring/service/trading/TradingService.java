package com.techstack.spring.service.trading;

import org.springframework.stereotype.Component;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Component
public class TradingService {
	
	private Long dealerNumber;
	
	private Long currentMileage;
	
	public void getDealerNumber() {
		System.out.println("Given dealer number is " + this.dealerNumber);
	}
	
	public void setDealerNumber(Long dealerNumber) {
		this.dealerNumber = dealerNumber;
	}
	
	public void getCurrentMileage() {
		System.out.println("This is a valid mileage charge " + this.currentMileage);
	}
	
	public void setCurrentMileage(Long currentMileage) {
		this.currentMileage = currentMileage;
	}
	
}