/**
 * 
 */
package com.techstack.spring.account.dao;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
/**
 * @author KARTHIKEYAN N
 *
 */
public @interface Auditable {

}
