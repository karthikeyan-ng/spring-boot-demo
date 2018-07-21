/**
 * 
 */
package com.techstack.spring.annotations.qualifier.multiplequalifierparams;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@ComponentScan(basePackages = "com.techstack.spring.annotations.qualifier.multiplequalifierparams")
public class AppConfig {
	
	@Bean(name = "actionVhsCatalog")
    public MovieCatalog actionVhsCatalog() {
		MovieCatalog first = new MovieCatalog();
		first.setCatalogname("Action Movie in VHS Catalog");
		return first;
	}
	
	@Bean(name = "comedyVhsCatalog")
    public MovieCatalog comedyVhsCatalog() { 
		MovieCatalog first = new MovieCatalog();
		first.setCatalogname("Comedy Movie in VHS Catalog");
		return first;
	}
	
	@Bean(name = "actionDvdCatalog")
    public MovieCatalog actionDvdCatalog() { 
		MovieCatalog first = new MovieCatalog();
		first.setCatalogname("Action Movie in DVD Catalog");
		return first;
	}
	
	@Bean(name = "comedyBluRayCatalog")
    public MovieCatalog comedyBluRayCatalog() { 
		MovieCatalog first = new MovieCatalog();
		first.setCatalogname("Comedy Movie in BluRay Catalog");
		return first;
	}

}