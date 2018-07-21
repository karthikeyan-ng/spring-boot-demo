package com.techstack.spring.aop;

import java.util.Arrays;
import java.util.Collection;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.techstack.spring.account.dao.Auditable;
import com.techstack.spring.account.entity.Account;
import com.techstack.spring.service.MyType;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Aspect
@Component
@Order(2)
public class LoggerAspect {

	/*
	 *  //Format
	 *	execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern)
     *	throws-pattern?)
	 * 
	 * execution(* com.techstack.spring.service.HiByeService.sayHi())  ==> pointcut with fully qualified expression, only execute when sayHi called with zero argument
	 * execution(* com.techstack.spring.service.HiByeService.sayHi(*)) ==> pointcut with fully qualified expression which matches a method taking one parameter of any type
	 * execution(* com.techstack.spring.service.HiByeService.sayHi(..)) ==> pointcut with fully qualified expression, only execute when sayHi with any params (including zero argument)
	 * execution(* com.techstack.spring.service.HiByeService.sayHi(*, Integer)) ==> pointcut with fully qualified expression, only execute when sayHi with matches a method taking two parameters, the first can be of any type, the second must be a Integer.
	 * execution(* com.techstack.spring.service.HiByeService.*(*, Integer)) ==> only execute when HelloService class invoked that matches any method taking two parameters, the first can be of any type, the second must be a Integer (if your argument type is small int, it will not work).
	 * execution(public * com.techstack.spring.service.HiByeService.*(..)) ==> only execute all public method signature which contains any params
	 * execution(public * *(..)) ==> any public method in any package/classes/method
	 * execution(public * get*(..)) ==> any public get*** method in any package/classes
	 * execution(public * set*(..)) ==> any public set*** method in any package/classes
	 * within(com.techstack.spring.service.trading..*) ==> within trading package/ it's all sub packages and classes
	 * within(com.techstack.spring..*) ==> within base groupId (base package and it's all classes)
	 */
	
	@Pointcut("execution(public * *(..))")
	private void anyPublicOperation() {}
	
	@Pointcut("within(com.techstack.spring.service.trading..*)")
	private void inTrading() {}
	
	@Pointcut("anyPublicOperation() && inTrading()")
	private void tradingOperation() {}
	
	@Before("execution(* com.techstack.spring.service.HiByeService.sayHi())")
	public void beforeMethodExecution(JoinPoint joinPoint) {
		System.out.print("Before ");
		System.out.print(joinPoint.getSignature().getName() + " called with ");
		System.out.println(Arrays.toString(joinPoint.getArgs()));
	}
	
//	@Before("execution(* com.techstack.spring.service.HiByeService.*(..))")
	public void beforeMethodExecutionWithIncomingArgs(JoinPoint joinPoint) {
		System.out.print("Before ");
		System.out.print(joinPoint.getSignature().getName() + " called with ");
		System.out.println(Arrays.toString(joinPoint.getArgs()));
	}

//	@After("execution(* com.techstack.spring.service.HiByeService.*(..))")
	public void afterMethodExecution(JoinPoint joinPoint) {
		System.out.print("After ");
		System.out.print(joinPoint.getSignature().getName() + " called with ");
		System.out.println(Arrays.toString(joinPoint.getArgs()));
	}
	
//	@AfterReturning(pointcut = "execution(* com.techstack.spring.service.HiByeService.*(..))", returning = "result")
	public void afterMethodExecutionWithReturingValue(JoinPoint joinPoint, Object result) {
		System.out.print("After ");
		System.out.print(joinPoint.getSignature().getName());
		System.out.println(" result is " + result);
	}
	
//	@AfterThrowing("execution(* com.techstack.spring.service.HiByeService.*(..))")
    public void doRecoveryActions() {
        // ...
    }
	
//	@AfterThrowing(
//			pointcut = "execution(* com.techstack.spring.service.HiByeService.*(..))", 
//			throwing = "ex")
	public void doRecoveryActions(Exception ex) {
		//ex.printStackTra
		//ex.getMessage();
	}

	
//	@Around("execution(* com.techstack.spring.service.HiByeService.*(..))")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        // start stopwatch
        Object retVal = pjp.proceed();
        // stop stopwatch
        return retVal;
    }
    
//============================================================
	/**
     * A data access operation is the execution of any method defined on a
     * dao interface. This definition assumes that interfaces are placed in the
     * "dao" package, and that implementation types are in sub-packages.
     */
    @Pointcut("execution(* com.techstack.spring.account.dao.*.*(..))")
    private void dataAccessOperation() {}
    
    @Pointcut("dataAccessOperation() && args(account,..)")
	private void accountDataAccessOperation(Account account) {}
    
    /**
     * Suppose you want to advise the execution of dao operations that take an Account object as the first parameter, 
	 * and you need access to the account in the advice body. You could write the following:
     * 
     * @param account
     */
	//@Before("dataAccessOperation() && args(account,..)")
   // @Before("accountDataAccessOperation(account)")
	public void validateAccount(Account account) {
    	System.out.println("Is account active : " + account.isActive());
	}
    
//============================================================
    
    /**
     * The following example shows how you could match the execution of methods annotated 
     * with an @Auditable annotation, and extract the audit code.
     */
    @Around("@annotation(com.techstack.spring.account.dao.Auditable)")
	public void audit(ProceedingJoinPoint pjp) throws Throwable {
    	long start = System.nanoTime();
    	
    	// start stopwatch
        Object retVal = pjp.proceed();
        // stop stopwatch
    	double needed = System.nanoTime() - start;
    	
    	needed /= 1000000d;
		System.out.println("Total time taken is " + String.format("%10.3f", needed) + " ms");
	}
    
    @Before("dataAccessOperation() && @annotation(com.techstack.spring.account.dao.Loggable)")
    public void methodEntryLog(JoinPoint joinPoint) {
    	System.out.print("Before ");
		System.out.print(joinPoint.getSignature().getName() + " called with ");
		System.out.println(Arrays.toString(joinPoint.getArgs()));
    }
    
    @After("dataAccessOperation() && @annotation(com.techstack.spring.account.dao.Loggable)")
    public void methodExitLog(JoinPoint joinPoint) {
    	System.out.print("After ");
		System.out.print(joinPoint.getSignature().getName() + " called with ");
		System.out.println(Arrays.toString(joinPoint.getArgs()));
    }
    
//============================================================
    
    /**
     * Advice parameters and generics
	 * Spring AOP can handle generics used in class declarations and method parameters.
	 * 
	 * You can restrict interception of method types to certain parameter types by simply typing 
	 * the advice parameter to the parameter type you want to intercept the method for:
     */
	@Before("execution(* com.techstack.spring.service.Sample+.sampleGenericMethod(*)) && args(param)")
	public void beforeSampleMethod(MyType param) {
	    System.out.println("Entered beforeSampleMethod");
	}
	
	@Before("execution(* com.techstack.spring.service.Sample+.sampleGenericCollectionMethod(*)) && args(param)")
	public void beforeSampleMethod(Collection<MyType> param) {
		System.out.println("Entered beforeSampleMethod collection");
	}
	
	/**
	 * To make this work we would have to inspect every element of the collection, 
	 * which is not reasonable as we also cannot decide how to treat null values in general. 
	 * To achieve something similar to this you have to type the parameter to Collection<?> and manually check the type of the elements.
	 */
//	@Before("execution(* ..Sample+.sampleGenericCollectionMethod(*)) && args(param)")
//	public void beforeSampleMethod(Collection<?> param) {
//	    // Advice implementation
//	}
	
//============================================================
	
	/**
	 * Determining argument names
	 *
	 * If the parameter names have been specified by the user explicitly, then the specified parameter names are used: 
	 * both the advice and the pointcut annotations have an optional "argNames" attribute 
	 * which can be used to specify the argument names of the annotated method - these argument names are available at runtime
	 */
	
//	@Before(value="execution(* com.techstack.spring.account.dao.*.*(..)) && target(bean) && @annotation(auditable)",
//        	argNames="bean,auditable")
	public void audit(Object bean, Auditable auditable) {
	    // ... use auditable and bean
		System.out.println("Inside Audit " + bean);
	}
	
	/**
	 * If the first parameter is of the JoinPoint, ProceedingJoinPoint, or JoinPoint.StaticPart type, 
	 * you may leave out the name of the parameter from the value of the "argNames" attribute. 
 	 * For example, if you modify the preceding advice to receive the join point object, the "argNames" attribute need not include it:
	 */
	
	@Before(value="execution(* com.techstack.spring.account.dao.*.*(..)) && target(bean) && @annotation(auditable)",
	        argNames="bean,auditable")
	public void audit(JoinPoint jp, Object bean, Auditable auditable) {
	    // ... use auditable, bean, and jp
		System.out.println("Inside Audit " + bean);
	}
	
//============================================================
    
	/**
	 * Proceeding with arguments
	 * 
	 * how to write a proceed call with arguments that works consistently across Spring AOP and AspectJ.
	 * The solution is simply to ensure that the advice signature binds each of the method parameters in order. For example: 
	 */
	
	@Pointcut("within(com.techstack.spring.account.dao..*)")
    private void inAccountDataAccessLayer() {}
	
	@Around("inAccountDataAccessLayer() && " +
	        "execution(java.util.List<com.techstack.spring.account.entity.Account> *..*find*(..)) && " +
	        "args(accountHolderNamePattern)")
	public Object preProcessQueryPattern(ProceedingJoinPoint pjp,
	        String accountHolderNamePattern) throws Throwable {
	    String newPattern = preProcess(accountHolderNamePattern);
	    return pjp.proceed(new Object[] {newPattern});
	}
	
	private String preProcess(String accountHolderNamePattern) {
		return accountHolderNamePattern + "***";
	}
	
//============================================================
	
	/**
	 * 	Advice ordering
	
		What happens when multiple pieces of advice all want to run at the same join point? 
			Spring AOP follows the same precedence rules as AspectJ to determine the order of advice execution. 
			The highest precedence advice runs first "on the way in" (so given two pieces of before advice, the one with highest precedence runs first). 
			"On the way out" from a join point, the highest precedence advice runs last 
			(so given two pieces of after advice, the one with the highest precedence will run second).
			
		When two pieces of advice defined in different aspects both need to run at the same join point, 
		unless you specify otherwise the order of execution is undefined. You can control the order of execution by specifying precedence. 
		This is done in the normal Spring way by either implementing the org.springframework.core.Ordered interface 
		in the aspect class or annotating it with the Order annotation. Given two aspects, 
		the aspect returning the lower value from Ordered.getValue() (or the annotation value) has the higher precedence.
	
		When two pieces of advice defined in the same aspect both need to run at the same join point, 
		the ordering is undefined (since there is no way to retrieve the declaration order via reflection for javac-compiled classes). 
		Consider collapsing such advice methods into one advice method per join point in each aspect class, or refactor 
		the pieces of advice into separate aspect classes - which can be ordered at the aspect level.
	 */
	
	 /**
	  * Declaring around advice for all transfer methods whose taking three arguments of any type 
	  * of all classes in the package com.techstack.spring.service
	  * @param jp
	  * @throws Throwable
	  */
	 @Around("execution(* com.techstack.spring.service.*.transfer(*,*,*))")
	 public void beforeAdviceForTransferMethods(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("****LoggingAspect Before execution of Transfer Methods " + pjp.getSignature().getName());
        pjp.proceed();
        System.out.println("****LoggingAspect After execution of Transfer Methods " + pjp.getSignature().getName());
	 }
	 
}















/**

	//Simple Pointcut

	@Pointcut("execution(* transfer(..))")	// the pointcut expression
	private void anyOldTransfer() {} // the pointcut signature


	@Pointcut("execution(public * *(..))")	//any public method in any package/classes
	private void anyPublicOperation() {}
	
	@Pointcut("within(com.xyz.someapp.trading..*)")		//within trading package/classes
	private void inTrading() {}
	
	@Pointcut("anyPublicOperation() && inTrading()")	//combine one or more Pointcut method using &&, ||, !  
	private void tradingOperation() {}

	//Format
	execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern)
    	throws-pattern?)
	
    
  	The parameters pattern is slightly more complex: 
	  	() matches a method that takes no parameters, whereas 
	  	(..) matches any number of parameters (zero or more). The pattern 
	  	(*) matches a method taking one parameter of any type, 
	  	(*,String) matches a method taking two parameters, the first can be of any type, the second must be a String.  
	  	
	More examples, refer
		https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#aop-pointcuts-examples

===================
	Suppose you want to advise the execution of dao operations that take an Account object as the first parameter, 
	and you need access to the account in the advice body. You could write the following:
	
	@Before("com.xyz.myapp.SystemArchitecture.dataAccessOperation() && args(account,..)")
	public void validateAccount(Account account) {
    	// ...
	}
	
	The args(account,..) part of the pointcut expression serves two purposes: 
	firstly, it restricts matching to only those method executions where the method takes at least one parameter, 
	and the argument passed to that parameter is an instance of Account; 
	secondly, it makes the actual Account object available to the advice via the account parameter.
	
	Another way
	-----------
	@Pointcut("com.xyz.myapp.SystemArchitecture.dataAccessOperation() && args(account,..)")
	private void accountDataAccessOperation(Account account) {}

	@Before("accountDataAccessOperation(account)")
	public void validateAccount(Account account) {
	    // ...
	}
===================	
	The following example shows how you could match the execution of methods annotated with an @Auditable annotation, and extract the audit code.
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface Auditable {
	    AuditCode value();
	}
	
	And then the advice that matches the execution of @Auditable methods:
	
	@Before("com.xyz.lib.Pointcuts.anyPublicMethod() && @annotation(auditable)")
	public void audit(Auditable auditable) {
	    AuditCode code = auditable.value();
	    // ...
	}
===================	
	Advice parameters and generics
	Spring AOP can handle generics used in class declarations and method parameters. Suppose you have a generic type like this:
	
	public interface Sample<T> {
	    void sampleGenericMethod(T param);
	    void sampleGenericCollectionMethod(Collection<T> param);
	}
	
	You can restrict interception of method types to certain parameter types by simply typing 
	the advice parameter to the parameter type you want to intercept the method for:
	
	@Before("execution(* ..Sample+.sampleGenericMethod(*)) && args(param)")
	public void beforeSampleMethod(MyType param) {
	    // Advice implementation
	}
	
	it’s worth pointing out that this won’t work for generic collections. So you cannot define a pointcut like this:
	
	@Before("execution(* ..Sample+.sampleGenericCollectionMethod(*)) && args(param)")
	public void beforeSampleMethod(Collection<MyType> param) {
	    // Advice implementation
	}
	
	To make this work we would have to inspect every element of the collection, 
	which is not reasonable as we also cannot decide how to treat null values in general. 
	To achieve something similar to this you have to type the parameter to Collection<?> and manually check the type of the elements.
	
	@Before("execution(* ..Sample+.sampleGenericCollectionMethod(*)) && args(param)")
	public void beforeSampleMethod(Collection<?> param) {
	    // Advice implementation
	}
===================
	Determining argument names
	
	If the parameter names have been specified by the user explicitly, then the specified parameter names are used: 
	both the advice and the pointcut annotations have an optional "argNames" attribute 
	which can be used to specify the argument names of the annotated method - these argument names are available at runtime. For example:
	
	@Before(value="com.xyz.lib.Pointcuts.anyPublicMethod() && target(bean) && @annotation(auditable)",
        	argNames="bean,auditable")
	public void audit(Object bean, Auditable auditable) {
	    AuditCode code = auditable.value();
	    // ... use code and bean
	}
	
	If the first parameter is of the JoinPoint, ProceedingJoinPoint, or JoinPoint.StaticPart type, 
	you may leave out the name of the parameter from the value of the "argNames" attribute. 
	For example, if you modify the preceding advice to receive the join point object, the "argNames" attribute need not include it:
	
	@Before(value="com.xyz.lib.Pointcuts.anyPublicMethod() && target(bean) && @annotation(auditable)",
        	argNames="bean,auditable")
	public void audit(JoinPoint jp, Object bean, Auditable auditable) {
	    AuditCode code = auditable.value();
	    // ... use code, bean, and jp
	}
===================
	Proceeding with arguments
	
	how to write a proceed call with arguments that works consistently across Spring AOP and AspectJ. 
	The solution is simply to ensure that the advice signature binds each of the method parameters in order. For example:
	
	@Around("execution(List<Account> find*(..)) && " +
	        "com.xyz.myapp.SystemArchitecture.inDataAccessLayer() && " +
	        "args(accountHolderNamePattern)")
	public Object preProcessQueryPattern(ProceedingJoinPoint pjp,
	        String accountHolderNamePattern) throws Throwable {
	    String newPattern = preProcess(accountHolderNamePattern);
	    return pjp.proceed(new Object[] {newPattern});
	}
===================
	Advice ordering
	
	What happens when multiple pieces of advice all want to run at the same join point? 
		Spring AOP follows the same precedence rules as AspectJ to determine the order of advice execution. 
		The highest precedence advice runs first "on the way in" (so given two pieces of before advice, the one with highest precedence runs first). 
		"On the way out" from a join point, the highest precedence advice runs last 
		(so given two pieces of after advice, the one with the highest precedence will run second).
		
	When two pieces of advice defined in different aspects both need to run at the same join point, 
	unless you specify otherwise the order of execution is undefined. You can control the order of execution by specifying precedence. 
	This is done in the normal Spring way by either implementing the org.springframework.core.Ordered interface 
	in the aspect class or annotating it with the Order annotation. Given two aspects, 
	the aspect returning the lower value from Ordered.getValue() (or the annotation value) has the higher precedence.

	When two pieces of advice defined in the same aspect both need to run at the same join point, 
	the ordering is undefined (since there is no way to retrieve the declaration order via reflection for javac-compiled classes). 
	Consider collapsing such advice methods into one advice method per join point in each aspect class, or refactor 
	the pieces of advice into separate aspect classes - which can be ordered at the aspect level.
===================
	Introductions
	
	Introductions (known as inter-type declarations in AspectJ) enable an aspect to declare 
	that advised objects implement a given interface, and to provide an implementation of 
	that interface on behalf of those objects.

	An introduction is made using the @DeclareParents annotation. This annotation is used to 
	declare that matching types have a new parent (hence the name). 
	For example, given an interface UsageTracked, and an implementation of 
	that interface DefaultUsageTracked, the following aspect declares 
	that all implementors of service interfaces also implement the UsageTracked interface. 
	(In order to expose statistics via JMX for example.)
	
	@Aspect
	public class UsageTracking {
	
	    @DeclareParents(value="com.xzy.myapp.service.*+", defaultImpl=DefaultUsageTracked.class)
	    public static UsageTracked mixin;
	
	    @Before("com.xyz.myapp.SystemArchitecture.businessService() && this(usageTracked)")
	    public void recordUsage(UsageTracked usageTracked) {
	        usageTracked.incrementUseCount();
	    }
	
	}
	
	The interface to be implemented is determined by the type of the annotated field. The value attribute of the @DeclareParents annotation is an AspectJ type pattern :- any bean of a matching type will implement the UsageTracked interface. Note that in the before advice of the above example, service beans can be directly used as implementations of the UsageTracked interface.
===================
	Aspect instantiation models (advance topic)
	
	By default there will be a single instance of each aspect within the application context. AspectJ calls this the singleton instantiation model. It is possible to define aspects with alternate lifecycles :- Spring supports AspectJ’s perthis and pertarget instantiation models ( percflow, percflowbelow, and pertypewithin are not currently supported).

	A "perthis" aspect is declared by specifying a perthis clause in the @Aspect annotation. Let’s look at an example, and then we’ll explain how it works.
	
	@Aspect("perthis(com.xyz.myapp.SystemArchitecture.businessService())")
	public class MyAspect {

	    private int someState;
	
	    @Before(com.xyz.myapp.SystemArchitecture.businessService())
	    public void recordServiceUsage() {
	        // ...
	    }
	
	}
	
	The effect of the 'perthis' clause is that one aspect instance will be created for each unique service object executing a business service (each unique object bound to 'this' at join points matched by the pointcut expression). The aspect instance is created the first time that a method is invoked on the service object. The aspect goes out of scope when the service object goes out of scope. Before the aspect instance is created, none of the advice within it executes. As soon as the aspect instance has been created, the advice declared within it will execute at matched join points, but only when the service object is the one this aspect is associated with. See the AspectJ programming guide for more information on per-clauses.

	The 'pertarget' instantiation model works in exactly the same way as perthis, but creates one aspect instance for each unique target object at matched join points.
===================
	
	
*/