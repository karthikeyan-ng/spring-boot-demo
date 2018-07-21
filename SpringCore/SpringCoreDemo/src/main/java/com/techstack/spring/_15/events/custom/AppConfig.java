/**
 * 
 */
package com.techstack.spring._15.events.custom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@ComponentScan(basePackages = "com.techstack.spring._15.events.custom")
public class AppConfig {
	
	@Bean
	public EmailService createEmailService() {
		EmailService emailService = new EmailService();
		List<String> blackListEmails = new ArrayList<>();
		blackListEmails.add("known.spammer@example.org");
		blackListEmails.add("known.hacker@example.org");
		blackListEmails.add("john.doe@example.org");
		emailService.setBlackList(blackListEmails);
		return emailService;
	}
	
	@Bean
	public BlackListNotifier createBlackListNotifierBean() {
		BlackListNotifier notifier = new BlackListNotifier();
		notifier.setNotificationAddress("blacklist@example.org");
		return notifier;
	}
	
	@Bean
	public Person createPerson() {
		Person person = new Person();
		person.setName("Karthikeyan");
		return person;
	}
}