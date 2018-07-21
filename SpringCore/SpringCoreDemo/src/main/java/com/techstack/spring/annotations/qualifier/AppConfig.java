/**
 * 
 */
package com.techstack.spring.annotations.qualifier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@ComponentScan(basePackages = "com.techstack.spring.annotations.qualifier")
public class AppConfig {
	
	@Bean(name = "horror")
    public MovieCatalog firstMovieCatalog() { 
		MovieCatalog first = new MovieCatalog();
		first.setCatalogname("Horror Movie Catalog");
		return first;
	}
	
	@Bean(name = "action")
    public MovieCatalog secondMovieCatalog() {
		MovieCatalog second = new MovieCatalog();
		second.setCatalogname("Action Movie Catalog");
		return second;
	}

}