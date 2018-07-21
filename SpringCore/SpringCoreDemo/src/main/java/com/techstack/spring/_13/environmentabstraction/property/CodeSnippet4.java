/**
 * 
 */
package com.techstack.spring._13.environmentabstraction.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.techstack.spring.beandefinitioninheritance.TestBean;

/**
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@PropertySource("classpath:/com/${my.placeholder:default/path}/app.properties")
public class CodeSnippet4 {

	@Autowired
    Environment env;

    @Bean
    public TestBean testBean() {
        TestBean testBean = new TestBean();
        testBean.setName(env.getProperty("testbean.name"));
        return testBean;
    }
}



/* 
   Assuming that "my.placeholder" is present in one of the property sources already registered, 
   e.g. system properties or environment variables, 
   the placeholder will be resolved to the corresponding value. If not, then "default/path" will be used as a default. 
   If no default is specified and a property cannot be resolved, an IllegalArgumentException will be thrown.
   
*/
   
    
