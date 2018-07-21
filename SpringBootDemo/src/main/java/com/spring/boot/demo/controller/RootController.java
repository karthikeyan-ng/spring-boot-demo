package com.spring.boot.demo.controller;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.demo.mail.MailSender;
import com.spring.boot.demo.mail.SmtpMailSender;

@RestController
public class RootController {

//	@Resource @Qualifier
//	private MailSender mailSender;
	
	@Resource
	private SmtpMailSender smtpMailSender;
	
	@RequestMapping("/")
	public String home() {
		smtpMailSender.send("test@gmail.com", "TestMail!", "Test Message!!!");
		return "Hello best Test World!";
	}
	
	@RequestMapping("/send-mail")
	public void sendMail() throws MessagingException {
		smtpMailSender.sendMailToGmail("karthikeyan.ng@gmail.com", "TestMail!", "Test Message!!!");
	}
}
