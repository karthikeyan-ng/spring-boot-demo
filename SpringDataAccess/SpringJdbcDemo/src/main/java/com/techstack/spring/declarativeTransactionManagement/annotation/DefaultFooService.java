/**
 * 
 */
package com.techstack.spring.declarativeTransactionManagement.annotation;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
//==>also supported as a drop-in replacement to Spring’s own annotation. Please refer to JTA 1.2 documentation for more details.
//import javax.transaction.Transactional;

/**
 * This example uses Foo and Bar classes as placeholders so that you can concentrate on the transaction usage 
 * without focusing on a particular domain model. For the purposes of this example, the fact that the 
 * DefaultFooService class throws UnsupportedOperationException instances in the body of each implemented method is good; 
 * it allows you to see transactions created and then rolled back in response to the UnsupportedOperationException instance.
 * 
 * Assume that the first two methods of the FooService interface, getFoo(String) and getFoo(String, String), must execute 
 * in the context of a transaction with read-only semantics
 * 
 * and that the other methods, insertFoo(Foo) and updateFoo(Foo), must execute in the context of a transaction with read-write semantics
 * 
 * @author KARTHIKEYAN N
 *
 */
@Transactional(readOnly = true)
public class DefaultFooService implements FooService {

	@Override
	public Foo getFoo(String fooName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Foo getFoo(String fooName, String barName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void insertFoo(Foo foo) {
		throw new UnsupportedOperationException();
		
	}

	// these settings have precedence for this method //here readOnly is higher precedence than class level readonly
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void updateFoo(Foo foo) {
		throw new UnsupportedOperationException();
		
	}
    
    
    /**
     * @Transactional settings
     * 
     * for example, "start a brand new read-only transaction when this method is invoked, suspending any existing transaction". 
     * The default @Transactional settings are as follows:
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void updateFoo1(Foo foo) {
		throw new UnsupportedOperationException();
		
	}

}
