package com.spring.boot.demo.mail;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockMailSender implements MailSender {
	
	private static final Logger log = LoggerFactory.getLogger(MockMailSender.class);
	
	/* (non-Javadoc)
	 * @see com.spring.boot.demo.mail.MailSender#send(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void send(String to, String subject, String body) {
		log.info("Sending mail to " + to);
		log.info("Subject: " + subject);
		log.info("Body: " + body);
		
	}

	@Override
	public void sendMailToGmail(String to, String subject, String body) throws MessagingException {
		// TODO Auto-generated method stub
		
	}

}
