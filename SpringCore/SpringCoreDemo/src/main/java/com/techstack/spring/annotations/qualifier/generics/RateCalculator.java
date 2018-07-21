/**
 * 
 */
package com.techstack.spring.annotations.qualifier.generics;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author KARTHIKEYAN N
 *
 */
public class RateCalculator {

	@Autowired
	private RateFormatter<BigDecimal> formatter;
	
	@Autowired
	private RateFormatter<Integer> integerFormatter;

	public void calculate() {
		BigDecimal rate = new BigDecimal(Math.random() * 100);
		System.out.println(formatter.format(rate));
		
		System.out.println(integerFormatter.format(123353));
	}
}
