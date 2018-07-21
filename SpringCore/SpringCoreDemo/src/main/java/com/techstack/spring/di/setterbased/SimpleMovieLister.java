package com.techstack.spring.di.setterbased;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Component
public class SimpleMovieLister {

	// the SimpleMovieLister has a dependency on a MovieFinder
    private MovieFinder movieFinder;

    /**
     * Caused by: org.springframework.beans.factory.BeanInitializationException: Property 'movieFinder' is 
     * required for bean 'employee'
     * 
     * @param movieFinder
     */
    // a setter method so that the Spring container can inject a MovieFinder
    @Required
    public void setMovieFinder(MovieFinder movieFinder) {
		this.movieFinder = movieFinder;
	}

    // business logic that actually uses the injected MovieFinder is omitted...
    public String findMovieById(int id) {
    	return movieFinder.findById(id);
    }
}
