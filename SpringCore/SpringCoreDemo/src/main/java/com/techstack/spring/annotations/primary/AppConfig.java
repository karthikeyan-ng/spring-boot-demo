/**
 * 
 */
package com.techstack.spring.annotations.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@ComponentScan(basePackages = "com.techstack.spring.annotations.primary")
public class AppConfig {
	
	@Bean
	@Primary
    public MovieCatalog firstMovieCatalog() { 
		MovieCatalog first = new MovieCatalog();
		first.setCatalogname("First Movie Catalog");
		return first;
	}
	
	@Bean
	
    public MovieCatalog secondMovieCatalog() {
		MovieCatalog second = new MovieCatalog();
		second.setCatalogname("Second Movie Catalog");
		return second;
	}

}