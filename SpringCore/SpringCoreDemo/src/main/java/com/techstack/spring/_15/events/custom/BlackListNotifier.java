/**
 * 
 */
package com.techstack.spring._15.events.custom;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * To receive the custom ApplicationEvent, create a class that implements ApplicationListener and register it 
 * as a Spring bean. The following example demonstrates such a class:
 * 
 * Notice that ApplicationListener is generically parameterized with the type of your custom event, BlackListEvent. 
 * This means that the onApplicationEvent() method can remain type-safe, avoiding any need for downcasting. 
 * You may register as many event listeners as you wish, but note that by default event listeners receive events 
 * synchronously. This means the publishEvent() method blocks until all listeners have finished processing the event. 
 * One advantage of this synchronous and single-threaded approach is that when a listener receives an event, 
 * it operates inside the transaction context of the publisher if a transaction context is available. 
 * If another strategy for event publication becomes necessary, refer to the javadoc for 
 * Spring’s ApplicationEventMulticaster interface.
 * 
 * @author KARTHIKEYAN N
 *
 */
@Component
public class BlackListNotifier implements ApplicationListener<BlackListEvent> {

    private String notificationAddress;

    public void setNotificationAddress(String notificationAddress) {
        this.notificationAddress = notificationAddress;
    }

    /**
     * OLD implementation
     * 
     * Before Spring 4.2 (OLD)
     */
    @Override
    public void onApplicationEvent(BlackListEvent event) {
        // notify appropriate parties via notificationAddress...
    	System.out.println("Black listers information has been notified to " + notificationAddress);
    }
    
    /**
     * NEW implementation
     * 
     * @EventListener is available from Spring 4.2 (NEW)
     * user defined event name (processBlackListEvent)
     * 
     * @param event
     */
//    @EventListener
//    public void processBlackListEvent(BlackListEvent event) {
//        // notify appropriate parties via notificationAddress...
//    	System.out.println("Event fired using @EventListener annotation");
//    	System.out.println("Black listers information has been notified to " + notificationAddress);
//    }
    
    
    /**
     * More than one event has been configured for the user defined method 
     * 
     * If your method should listen to several events or if you want to define it with no parameter at all, 
     * the event type(s) can also be specified on the annotation itself:
     */
//    @EventListener({ContextStartedEvent.class, ContextRefreshedEvent.class, BlackListEvent.class})
//    public void handleVariousEvents() {
//    	System.out.println("Executed array of events based on the event fired...");
//    }
    
    /**
     * Conditional event based on event property "test" value is "foo"
     * 
     * It is also possible to add additional runtime filtering via the condition attribute of the annotation that defines a 
     * SpEL expression that should match to actually invoke the method for a particular event.
     * 
     * For instance, our notifier can be rewritten to be only invoked if the test attribute of the event is equal to foo:
     * 
     * @param event
     */
//  @EventListener(condition = "#event.test == 'foo'")
//	public void processBlackListEvent(BlackListEvent event) {
//		// notify appropriate parties via notificationAddress...
//		System.out.println("BlackListEvent fired based on test property value 'foo'...");
//	}
    
    /**
     * If you need to publish an event as the result of processing another, just change the method signature to return 
     * the event that should be published, something like:
     * 
     * This feature is not supported for asynchronous listeners.
     * 
     * This new method will publish a new ListUpdateEvent for every BlackListEvent handled by the method below. 
     * If you need to publish several events, just return a Collection of events instead.
     * 
     * @param event
     * @return
     */
//    @EventListener
//    public ListUpdateEvent handleBlackListEvent(BlackListEvent event) {
//        // notify appropriate parties via notificationAddress and
//        // then publish a ListUpdateEvent...
//    }
    
    /**
     * Asynchronous Listeners
     * 
     * If you want a particular listener to process events asynchronously, simply reuse the regular @Async support:
     * 
     * Be aware of the following limitations when using asynchronous events:
     * 
     * 1. If the event listener throws an Exception it will not be propagated to the caller, 
     * 	  check AsyncUncaughtExceptionHandler for more details.
     * 
     * 2. Such event listener cannot send replies. If you need to send another event as 
     * 	  the result of the processing, inject ApplicationEventPublisher to send the event manually.
     * 
     * @param event
     */
//	@EventListener
//	@Async
//	public void processBlackListEvent(BlackListEvent event) {
//		// BlackListEvent is processed in a separate thread
//		System.out.println("Event fired using @EventListener annotation");
//		System.out.println("Black listers information has been notified to " + notificationAddress);
//	}
	
	/**
	 * Ordering listeners
	 * 
	 * If you need the listener to be invoked before another one, just add the @Order annotation to the method declaration:
	 * 
	 * @param event
	 */
//	@EventListener
//	@Order(42)
//	public void processBlackListEvent(BlackListEvent event) {
//	    // notify appropriate parties via notificationAddress...
//	}
    
    /**
     * Generic events
     * 
     * You may also use generics to further define the structure of your event. Consider an EntityCreatedEvent<T> where T is the 
     * type of the actual entity that got created.
     * 
     * You can create the following listener definition to only receive EntityCreatedEvent for a Person:
     * 
     * Due to type erasure, this will only work if the event that is fired resolves the generic parameter(s) on which the 
     * event listener filters on (that is something like class PersonCreatedEvent extends EntityCreatedEvent<Person> { …​ }).
     * 
     * In certain circumstances, this may become quite tedious if all events follow the same structure (as it should be the 
     * case for the event above). In such a case, you can implement ResolvableTypeProvider to guide the framework beyond 
     * what the runtime environment provides:
     * 
	 * This works not only for ApplicationEvent but any arbitrary object that you’d send as an event.
     * 
     * Refer: MainForClassCustomEvent.java AppConfig.java EntityCreatedEvent.java Person.java PersonEntityService.java
     * 
     * @param event
     */
    @EventListener
    public void onPersonCreated(EntityCreatedEvent<Person> event) {
        System.out.println(event.getSource().getClass().getSimpleName());
    }
    
}
