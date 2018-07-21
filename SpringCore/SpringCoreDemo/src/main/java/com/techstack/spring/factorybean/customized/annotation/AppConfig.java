/**
 * 
 */
package com.techstack.spring.factorybean.customized.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@ComponentScan(basePackages = "com.techstack.spring.factorybean.customized.annotation")
public class AppConfig {
	
	@Bean(name = "vehicle")
    public VehicleFactory vehicleFactory() {
		VehicleFactory factory = new VehicleFactory();
        factory.setFactoryId(7070);
        factory.setToolId(2);
        return factory;
    }
 
    @Bean
    public Vehicle vehicle() throws Exception {
        return vehicleFactory().getObject();
    }
}