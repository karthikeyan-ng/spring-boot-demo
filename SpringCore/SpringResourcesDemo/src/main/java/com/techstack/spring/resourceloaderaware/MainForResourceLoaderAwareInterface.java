package com.techstack.spring.resourceloaderaware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForResourceLoaderAwareInterface {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		//VehicleService vehicleService = context.getBean(VehicleService.class);
		
		//VehicleService2 vehicleService = context.getBean(VehicleService2.class);
		
		//VehicleService3 vehicleService = context.getBean(VehicleService3.class);
		
		VehicleService4 vehicleService = context.getBean(VehicleService4.class);
		
		Resource resource = vehicleService.getResource("file:///C://Workspaces//SPRING_Workspace//SpringCore//SpringResourcesDemo//src//main//java//format.properties");

		try {
			InputStream is = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	    	
		context.close();

	}

}
