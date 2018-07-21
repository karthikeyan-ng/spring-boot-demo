/**
 * 
 */
package com.techstack.spring.resourceloaderaware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

/**
 * Here, we have not used ResourceLoaderAware interface. Rather, we used
 * ResourceLoader to be created and injected while creation of VehicleService constructor. 
 * 
 * @author KARTHIKEYAN N
 *
 */
@Service
public class VehicleService2 {

	private ResourceLoader resourceLoader;
	
	@Autowired
	public VehicleService2(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	
	public Resource getResource(String location){
		return resourceLoader.getResource(location);
	}
	
}
