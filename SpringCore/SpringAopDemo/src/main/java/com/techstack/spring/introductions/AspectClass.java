package com.techstack.spring.introductions;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Aspect
@Component
public class AspectClass {

	@DeclareParents(
            value = "com.techstack.spring.introductions.Test+",
            defaultImpl = Test2Impl.class
    )
    public static ITest2 test2;
}
