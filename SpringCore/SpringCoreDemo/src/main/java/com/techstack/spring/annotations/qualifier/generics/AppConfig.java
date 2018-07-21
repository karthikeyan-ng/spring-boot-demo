/**
 * 
 */
package com.techstack.spring.annotations.qualifier.generics;

import java.math.BigDecimal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@ComponentScan(basePackages = "com.techstack.spring.annotations.qualifier.generics")
public class AppConfig {
	
	@Bean
    public RateFormatter<Integer> integerRateFormatter() {
        return new RateFormatter<Integer>();
    }

    @Bean
    public RateFormatter<BigDecimal> bigDecimalRateFormatter() {
        return new RateFormatter<BigDecimal>();
    }

    @Bean
    public RateCalculator rateCalculator() {
        return new RateCalculator();
    }

}