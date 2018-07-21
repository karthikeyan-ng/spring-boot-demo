/**
 * 
 */
package com.techstack.spring.annotations.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
@Component
public class MovieRecommender {
	
	@Autowired
	@Qualifier("horror")
    private MovieCatalog movieCatalog;
	
	public MovieCatalog printCurrentCatalog() {
		return movieCatalog;
	}

}
