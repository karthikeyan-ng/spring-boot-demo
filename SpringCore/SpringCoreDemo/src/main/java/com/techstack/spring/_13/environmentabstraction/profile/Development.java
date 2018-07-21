/**
 * 
 */
package com.techstack.spring._13.environmentabstraction.profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Profile;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Profile("development")
/**
 * @author KARTHIKEYAN N
 *
 */
public @interface Development {

}
