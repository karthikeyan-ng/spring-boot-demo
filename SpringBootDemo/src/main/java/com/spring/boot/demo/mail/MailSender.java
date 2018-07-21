package com.spring.boot.demo.mail;

import javax.mail.MessagingException;

public interface MailSender {

	void send(String to, String subject, String body);

	void sendMailToGmail(String to, String subject, String body) throws MessagingException;

}