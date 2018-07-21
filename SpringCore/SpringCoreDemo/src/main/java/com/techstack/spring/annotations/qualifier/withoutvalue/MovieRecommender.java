/**
 * 
 */
package com.techstack.spring.annotations.qualifier.withoutvalue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
@Component
public class MovieRecommender {
	
	@Autowired
	@Offline
    private MovieCatalog offlineCatalog;
	
	public MovieCatalog printOfflineCatalog() {
		return offlineCatalog;
	}

}
