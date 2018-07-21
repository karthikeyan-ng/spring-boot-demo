/**
 * 
 */
package com.techstack.spring.declarativeTransactionManagement.annotation;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author KARTHIKEYAN N
 *
 */
public class TransactionalService {

    @Transactional(value = "order")	//optionally specify the identity of the PlatformTransactionManager to be used
    								//This can either be the bean name or the qualifier value of the transaction manager bean
    public void setSomething(String name) { 
    	
    }

    @Transactional(value = "account")
    public void doSomething() { 
    	
    }
}


