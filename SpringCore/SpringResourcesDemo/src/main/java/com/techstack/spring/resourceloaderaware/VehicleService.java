/**
 * 
 */
package com.techstack.spring.resourceloaderaware;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

/**
 * VehicleService uses ResourceLoaderAware interface and setting 
 * ResourceLoader reference in {@link VehicleService#resourceLoader}
 * 
 * 
 * @author KARTHIKEYAN N
 *
 */
@Service
public class VehicleService implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;
	
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public Resource getResource(String location){
		return resourceLoader.getResource(location);
	}
	
}
