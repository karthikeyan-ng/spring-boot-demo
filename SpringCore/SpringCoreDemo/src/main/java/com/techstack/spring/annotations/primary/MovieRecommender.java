/**
 * 
 */
package com.techstack.spring.annotations.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
@Component
public class MovieRecommender {
	
	@Autowired
    private MovieCatalog movieCatalog;
	
	public MovieCatalog printCurrentCatalog() {
		return movieCatalog;
	}

}
