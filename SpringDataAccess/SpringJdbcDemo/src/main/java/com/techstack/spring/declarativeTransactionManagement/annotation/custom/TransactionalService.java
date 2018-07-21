/**
 * 
 */
package com.techstack.spring.declarativeTransactionManagement.annotation.custom;

/**
 * @author KARTHIKEYAN N
 *
 */
public class TransactionalService {

	@OrderTx
    public void setSomething(String name) { 
    	
    }

	@AccountTx
    public void doSomething() { 
    	
    }
}


