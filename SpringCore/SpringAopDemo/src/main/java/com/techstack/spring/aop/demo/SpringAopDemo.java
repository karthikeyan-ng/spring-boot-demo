package com.techstack.spring.aop.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.techstack.spring.account.entity.Account;
import com.techstack.spring.account.service.AccountService;
import com.techstack.spring.configuration.AppConfig;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class SpringAopDemo {

	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//===========================================		
		//Basic examples of Aspects
		
//		HiByeService service = context.getBean(HiByeService.class);	//target -->HiByeService proxy || this --> Aspect proxy instance
//		service.sayHi();
//		service.sayHi("test");
//		service.sayHi("Karthikeyan", 33);
//		service.test(Calendar.getInstance().getTime(), 2018);
//		service.sayBye();
//		service.returnSomething();
//		service.transfer("Karthi", 33, "India");
		
//		TradingService tradingService = context.getBean(TradingService.class);
//		tradingService.setDealerNumber(12345L);
//		tradingService.getDealerNumber();
//		tradingService.setCurrentMileage(12000L);
//		tradingService.getCurrentMileage();
//		
//		VehicleService vehicleService = context.getBean(VehicleService.class);
//		vehicleService.getVehicleInfo();
//		vehicleService.getVehiclePriceInfo();
		
		Account account = context.getBean(Account.class);
		account.setAccountNumber(1235);
		account.setAccountHolderName("Karthi");
		account.setActive(true);
		
//===========================================
		//receive and process target business method object in aspect class (Before aspect)
//		AccountService accountService = context.getBean(AccountService.class);
//		accountService.doValudate(account);
		
//===========================================		
		//methods annotated with custom annotation
		
//		AccountService accountService = context.getBean(AccountService.class);
//		accountService.doValudate(account);
//		accountService.updateAccount(account);
		
//===========================================		
		//Advice parameters and generics
		
//		Sample mySample = new MyType();
//		mySample.sampleGenericMethod(100);
//		Collection<Integer> collection = new ArrayList<Integer>();
//		collection.add(10);
//		mySample.sampleGenericCollectionMethod(collection);

//===========================================		
		//Determining argument names
		
//		AccountService accountService = context.getBean(AccountService.class);
//		accountService.doValudate(account);
//		accountService.updateAccount(account);
		
//===========================================		
		//Proceeding with arguments
		
//		AccountService accountService = context.getBean(AccountService.class);
//		accountService.findByAccountHolderName("Karthi");
		
//===========================================
		//Advice ordering (Refer LoggerAspect, SecurityAspect and TransactionAspect)
		
//		HiByeService service = context.getBean(HiByeService.class);
//		service.transfer("Karthi", 33, "India");
		
//===========================================
		//Introductions
		
//		Test test = (Test) context.getBean(Test.class);
//	    test.test1();
//	    ITest2 test2 = (ITest2) context.getBean(Test.class);
//	    test2.test2();
		
//===========================================
		//Instantiation
		
		
		//Singleton
//		VehicleService vehicleService = context.getBean(VehicleService.class);
//		vehicleService.getVehicleInfo();
//		
//		IService service;
//		
//		service = (IService) context.getBean("service");
//		
//		Parameter param = new Parameter("the text");
//		service.convert(param);
//		
//		param = new Parameter("the 2nd text");
//		service.convert(param);
//		
//		param = new Parameter("the 3rd text");
//		service.convert(param);
//		
//		IService service1 = (IService) context.getBean("service");
//		param = new Parameter("the 2nd instance text");
//		service1.convert(param);
		
//===========================================
		
		//Load time weaving (Refer ProfilingAspect.java and aop.xml)
		AccountService accountService = context.getBean(AccountService.class);
		accountService.retrieveAllRecords();
		
//===========================================		
		
	}
}
