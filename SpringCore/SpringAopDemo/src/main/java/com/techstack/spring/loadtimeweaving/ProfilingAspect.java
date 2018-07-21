/**
 * Let us assume that you are an application developer who has been tasked with 
 * diagnosing the cause of some performance problems in a system. 
 * Rather than break out a profiling tool, what we are going to do is switch on a 
 * simple profiling aspect that will enable us to very quickly get some performance metrics, 
 * so that we can then apply a finer-grained profiling tool to that specific area immediately afterwards.
 * 
 * steps:
 * ------
 * 		1. in application main method use @EnableLoadTimeWeaving or <context:load-time-weaver/> in XML configuration
 * 				To enable the Spring Framework’s LTW support, you need to configure a LoadTimeWeaver, which 
 * 				typically is done using the @EnableLoadTimeWeaving annotation.
 * 
 * 		2. create a META-INF/aop.xml file, to inform the AspectJ weaver that we want to weave our ProfilingAspect into our classes.
 * 				The AspectJ LTW infrastructure is configured using one or more META-INF/aop.xml files, 
 * 				that are on the Java classpath (either directly, or more typically in jar files).
 * 
 * 		3. extract bean from application context
 * 		4. LTW selectively on a per- ClassLoader basis with Spring, and this is true. 
 * 		   for this example, we are going to use a Java agent (supplied with Spring) to switch on the LTW.
 * 		5. Run configuration
 * 			set VMargs:	-javaagent:c:/temp/spring-instrument.jar
 * 
 * The Spring Framework ships with such an agent, the InstrumentationSavingAgent, 
 * which is packaged in the spring-instrument.jar that was supplied as the value of the -javaagent argument
 * 
 * TIP: The ProfilingAspect used in this example may be basic, but it is quite useful. 
 * 		It is a nice example of a development-time aspect that developers can use during development (of course), 
 * 		and then quite easily exclude from builds of the application being deployed into UAT or production.
 * 
 * Required libraries (JARS)
 * -------------------------
 * 		At a minimum you will need the following libraries to use the Spring Framework’s support for AspectJ LTW:
 *
 *		spring-aop.jar (version 2.5 or later, plus all mandatory dependencies)
 * 
 * 		aspectjweaver.jar (version 1.6.8 or later)
 *
 * 		If you are using the Spring-provided agent to enable instrumentation, you will also need:
 *
 *		spring-instrument.jar
 * 
 * LoadTimeWeaver implementation based on runtime environment
 * -----------------------------------------------------------
 * 		Runtime Environment							LoadTimeWeaver implementation
 * 	Running in Oracle’s WebLogic						WebLogicLoadTimeWeaver
 *  
 *  Running in Oracle’s GlassFish						GlassFishLoadTimeWeaver
 *  
 *  Running in Apache Tomcat							TomcatLoadTimeWeaver
 *  
 *  Running in Red Hat’s JBoss AS or WildFly			JBossLoadTimeWeaver
 *  
 *  Running in IBM’s WebSphere							WebSphereLoadTimeWeaver
 *  
 *  JVM started with Spring InstrumentationSavingAgent 
 *  (java -javaagent:path/to/spring-instrument.jar)		InstrumentationLoadTimeWeaver
 *  
 *  Fallback, expecting the underlying ClassLoader to 
 *  follow common conventions (e.g. applicable to 
 *  TomcatInstrumentableClassLoader and Resin)			ReflectiveLoadTimeWeaver
 *  
 * To specify a specific LoadTimeWeaver with Java configuration implement the LoadTimeWeavingConfigurer 
 * interface and override the getLoadTimeWeaver() method:
 * 		
 * 			@EnableLoadTimeWeaving
 * 			public class AppConfig implements LoadTimeWeavingConfigurer {
 * 				@Override
				public LoadTimeWeaver getLoadTimeWeaver() {
					return new WebLogicLoadTimeWeaver();
				}		
 * 			}
 * 
 * AspectJ weaving attribute values
 * --------------------------------
 * ENABLED | on ==> AspectJ weaving is on, and aspects will be woven at load-time as appropriate.
 * DISABLED | off ==> LTW is off…​ no aspect will be woven at load-time.
 * AUTODETECT(default) | autodetect ==> If the Spring LTW infrastructure can find at least one META-INF/aop.xml file, 
 * 										then AspectJ weaving is on, else it is off. This is the default value. 
 */

package com.techstack.spring.loadtimeweaving;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.LoadTimeWeavingConfigurer;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.instrument.classloading.weblogic.WebLogicLoadTimeWeaver;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Aspect
@Component
public class ProfilingAspect {

	@Around("methodsToBeProfiled()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch(getClass().getSimpleName());
        try {
            sw.start(pjp.getSignature().getName());
            return pjp.proceed();
        } finally {
            sw.stop();
            System.out.println(sw.prettyPrint());
        }
    }

    @Pointcut("execution(public * com.techstack.spring.account.dao.AccountDao.retrieveAllRecords(..))")
    public void methodsToBeProfiled(){}
    
}
