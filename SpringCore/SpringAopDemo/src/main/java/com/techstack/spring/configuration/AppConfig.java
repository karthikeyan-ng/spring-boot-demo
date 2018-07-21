package com.techstack.spring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;
import org.springframework.context.annotation.LoadTimeWeavingConfigurer;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.instrument.classloading.weblogic.WebLogicLoadTimeWeaver;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.techstack.spring.*" })
@EnableAspectJAutoProxy
//@EnableLoadTimeWeaving() //aspectjWeaving=AspectJWeaving.DISABLED) //Default: AUTODETECT
public class AppConfig { //implements LoadTimeWeavingConfigurer {

//	@Override
//	public LoadTimeWeaver getLoadTimeWeaver() {
//		return new WebLogicLoadTimeWeaver();
//	}
	
	//no custom defined configuration. Let Spring initialize all it's configuration by default.
	
	

}
