/**
 * 
 */
package com.techstack.spring.annotations.qualifier.withoutvalue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.techstack.spring.annotations.qualifier.custom.MovieCatalog;

/**
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@ComponentScan(basePackages = "com.techstack.spring.annotations.qualifier.withoutvalue")
public class AppConfig {
	
	@Bean
    public MovieCatalog createOffileMovieCatalog() { 
		MovieCatalog first = new MovieCatalog();
		first.setCatalogname("Offline Movie Catalog");
		return first;
	}
	
}