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
@PropertySource("classpath:/com/myco/app.properties")
public class CodeSnippet3 {

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
   The @PropertySource annotation provides a convenient and declarative mechanism for adding a PropertySource to Spring’s Environment.

   Given a file "app.properties" containing the key/value pair testbean.name=myTestBean, the following @Configuration class uses @PropertySource in such a 
   way that a call to testBean.getName() will return "myTestBean".
   
   
*/
   
    
