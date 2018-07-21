/**
 * 
 */
package com.techstack.spring.declarativeTransactionManagement;

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

	@Override
	public void updateFoo(Foo foo) {
		throw new UnsupportedOperationException();
		
	}

}
