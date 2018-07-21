/**
 * 
 */
package com.techstack.spring.annotations.autowired.arbitarynames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
@Component
public class ClientBean {

	private MovieCatalog movieCatalog;
	
	private CustomerPreferenceDao customerPreferenceDao;
	
	//You can also apply the annotation to methods with arbitrary names and/or multiple arguments:
	@Autowired
	public void prepare(MovieCatalog movieCatalog, CustomerPreferenceDao customerPreferenceDao) {
		this.movieCatalog = movieCatalog;
		this.customerPreferenceDao = customerPreferenceDao;
	}

	public void work() {
		System.out.println("movieCatalog: " + movieCatalog);
		System.out.println("customerPreferenceDao: " + customerPreferenceDao);
	}
}
