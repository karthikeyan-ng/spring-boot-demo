/**
 * 
 */
package com.techstack.spring.annotations.qualifier.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
@Component
public class MovieRecommender {
	
	@Autowired
	@Genre("horror")
    private MovieCatalog horrorMovieCatalog;
	
	public MovieCatalog printHorrorCatalog() {
		return horrorMovieCatalog;
	}

	private MovieCatalog comedyMovieCatalog;
	
	@Autowired
	public void setComedyMovieCatalog(@Genre("comedy") MovieCatalog comedyMovieCatalog) {
		this.comedyMovieCatalog = comedyMovieCatalog;
	}
	
	public MovieCatalog printComedyCatalog() {
		return comedyMovieCatalog;
	}
}
