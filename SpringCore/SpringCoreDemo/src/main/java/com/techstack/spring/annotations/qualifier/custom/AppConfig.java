/**
 * 
 */
package com.techstack.spring.annotations.qualifier.custom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@ComponentScan(basePackages = "com.techstack.spring.annotations.qualifier.custom")
public class AppConfig {
	
	@Genre("horror")
	@Bean
    public MovieCatalog firstMovieCatalog() { 
		MovieCatalog first = new MovieCatalog();
		first.setCatalogname("Horror Movie Catalog");
		return first;
	}
	
	@Genre("comedy")
	@Bean
    public MovieCatalog secondMovieCatalog() {
		MovieCatalog second = new MovieCatalog();
		second.setCatalogname("Comedy Movie Catalog");
		return second;
	}

}