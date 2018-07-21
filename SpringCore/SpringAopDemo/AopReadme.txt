Introduction:
=============
Aspect-Oriented Programming (AOP) complements Object-Oriented Programming (OOP) by providing another way of thinking about program structure. 
The key unit of modularity in OOP is the class, whereas in AOP the unit of modularity is the aspect. 
Aspects enable the modularization of concerns such as transaction management that cut across multiple types and objects. 
(Such concerns are often termed crosscutting concerns in AOP literature.)

AOP concepts and terminology
============================
		
Aspect: a modularization of a concern that cuts across multiple classes. 
		Transaction management is a good example of a crosscutting concern in enterprise Java applications. 
		In Spring AOP, aspects are implemented using regular classes (the schema-based approach) or 
		regular classes annotated with the @Aspect annotation (the @AspectJ style).
		
		Examples: Transaction, Logger, Security 
		
Join point: a point during the execution of a program, such as the execution of a method or the handling of an exception. 
			In Spring AOP, a join point always represents a method execution.
			
			
Advice: action taken by an aspect at a particular join point. Different types of advice include "around," "before" and "after" advice. 
		(Advice types are discussed below.) Many AOP frameworks, including Spring, model an advice as an interceptor, 
		maintaining a chain of interceptors around the join point.
		

Pointcut: a predicate that matches join points. Advice is associated with a pointcut expression and 
		  runs at any join point matched by the pointcut (for example, the execution of a method with a certain name). 
		  The concept of join points as matched by pointcut expressions is central to AOP, and Spring uses the AspectJ pointcut expression language by default.
		  
Introduction: declaring additional methods or fields on behalf of a type. 
			  Spring AOP allows you to introduce new interfaces (and a corresponding implementation) to any advised object. 
			  For example, you could use an introduction to make a bean implement an IsModified interface, to simplify caching. 
			  (An introduction is known as an inter-type declaration in the AspectJ community.)
			  
Target object: object being advised by one or more aspects. Also referred to as the advised object. 
			   Since Spring AOP is implemented using runtime proxies, this object will always be a proxied object.
			   
AOP proxy: an object created by the AOP framework in order to implement the aspect contracts (advise method executions and so on). 
		   In the Spring Framework, an AOP proxy will be a JDK dynamic proxy or a CGLIB proxy.
		   
Weaving: linking aspects with other application types or objects to create an advised object. 
		 This can be done at compile time (using the AspectJ compiler, for example), load time, or at runtime. 
		 Spring AOP, like other pure Java AOP frameworks, performs weaving at runtime.

Types of advice:
================

Before advice: Advice that executes before a join point, but which does not have the ability to prevent execution flow proceeding to the join point (unless it throws an exception).

After returning advice: Advice to be executed after a join point completes normally: for example, if a method returns without throwing an exception.

After throwing advice: Advice to be executed if a method exits by throwing an exception.

After (finally) advice: Advice to be executed regardless of the means by which a join point exits (normal or exceptional return).

Around advice: Advice that surrounds a join point such as a method invocation. This is the most powerful kind of advice. Around advice can perform custom behavior before and after the method invocation. It is also responsible for choosing whether to proceed to the join point or to shortcut the advised method execution by returning its own return value or throwing an exception.


Spring AOP capabilities and goals
=================================
# Spring AOP is implemented in pure Java. There is no need for a special compilation process. 
  Spring AOP does not need to control the class loader hierarchy, and is thus suitable for use in a Servlet container or application server.
 
# Spring AOP currently supports only method execution join points (advising the execution of methods on Spring beans). 
  Field interception is not implemented, although support for field interception could be added without breaking the core Spring AOP APIs. 
  If you need to advise field access and update join points, consider a language such as AspectJ.
  
# There are some things you cannot do easily or efficiently with Spring AOP, such as advise very fine-grained objects (such as domain objects typically): 
  AspectJ is the best choice in such cases.
  
# Spring AOP provides an excellent solution to most problems in enterprise Java applications that are amenable to AOP.

Spring AOP implementation approach
==================================
# You have the choice of AspectJ and/or Spring AOP
# @AspectJ - annotation style
# the Spring XML configuration-style approach (beans.xml)

Supported Pointcut Designators
==============================
	Spring AOP supports the following AspectJ pointcut designators (PCD) for use in pointcut expressions:

execution - for matching method execution join points, this is the primary pointcut designator you will use when working with Spring AOP

within - limits matching to join points within certain types (simply the execution of a method declared within a matching type when using Spring AOP)

this - limits matching to join points (the execution of methods when using Spring AOP) where the bean reference (Spring AOP proxy) is an instance of the given type

target - limits matching to join points (the execution of methods when using Spring AOP) where the target object (application object being proxied) is an instance of the given type

args - limits matching to join points (the execution of methods when using Spring AOP) where the arguments are instances of the given types

@target - limits matching to join points (the execution of methods when using Spring AOP) where the class of the executing object has an annotation of the given type

@args - limits matching to join points (the execution of methods when using Spring AOP) where the runtime type of the actual arguments passed have annotations of the given type(s)

@within - limits matching to join points within types that have the given annotation (the execution of methods declared in types with the given annotation when using Spring AOP)

@annotation - limits matching to join points where the subject of the join point (method being executed in Spring AOP) has the given annotation

	Other pointcut types (only available in AspectJ and not in Spring AOP)
		The full AspectJ pointcut language supports additional pointcut designators that are not supported in Spring. 
		These are: call, get, set, preinitialization, staticinitialization, initialization, handler, adviceexecution, withincode, cflow, cflowbelow, if, @this, and @withincode. 
		Use of these pointcut designators in pointcut expressions interpreted by Spring AOP will result in an IllegalArgumentException being thrown.

		The set of pointcut designators supported by Spring AOP may be extended in future releases to support more of the AspectJ pointcut designators.

Spring AOP is a proxy-based system and differentiates between the proxy object itself (bound to this) and the target object behind the proxy (bound to target).

=========================================================
//Static - Compile Time
//Aspect : Logging. Aspect is the concern that we are trying to implement generically. Ex: logging, transaction management. Advice is the specific aspect of the concern we are implementing.
//Pointcut : An expression which determines what are the methods that the Advice should be applied on.
//execution(* com.in28minutes.example.spring.business.aop.HiByeService.*(..))
//Advice: action taken by an aspect at a particular join point. Different types of advice include "around," "before" and "after" advice. (Advice types are discussed below.) Many AOP frameworks, including Spring, model an advice as an interceptor, maintaining a chain of interceptors around the join point.

//Dynamic - Runtime
//Join point: a point during the execution of a program, such as the execution of a method or the handling of an exception.
//In Spring AOP, a join point always represents a method execution.
//Weaving: linking aspects with other application types or objects to create an advised object. This can be done at compile time (using the AspectJ compiler, for example), load time, or at runtime. Spring AOP, like other pure Java AOP frameworks, performs weaving at runtime.

//Spring AOP is very simple implementation of AOP concepts. Its an ideal fit If the needs of an application are simple like
//AspectJ is a full fledged AOP framework that helps you
//Advise objects not managed by the Spring container .
//Advise join points other than simple method executions (for example, field get or set join points).