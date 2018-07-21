/**
 * 
 */
package com.techstack.spring.beanpostprocessor.customized.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@ComponentScan(basePackages = "com.techstack.spring.beanpostprocessor.customized.annotation")
public class AppConfig {
}