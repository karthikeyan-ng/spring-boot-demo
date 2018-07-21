/**
 * 
 */
package com.techstack.spring.declarativeTransactionManagement.annotation;

/**
 * This example uses Foo and Bar classes as placeholders so that you can concentrate on the transaction usage 
 * without focusing on a particular domain model. For the purposes of this example, the fact that the 
 * DefaultFooService class throws UnsupportedOperationException instances in the body of each implemented method is good; 
 * it allows you to see transactions created and then rolled back in response to the UnsupportedOperationException instance.
 * 
 * @author KARTHIKEYAN N
 *
 */
public interface FooService {

	Foo getFoo(String fooName);

	Foo getFoo(String fooName, String barName);

	void insertFoo(Foo foo);

	void updateFoo(Foo foo);

}
