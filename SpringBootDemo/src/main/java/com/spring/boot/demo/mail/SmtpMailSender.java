package com.spring.boot.demo.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component; 

//@Primary
//@Qualifier
@Component 
public class SmtpMailSender implements MailSender {
	
	private static final Logger log = LoggerFactory.getLogger(SmtpMailSender.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	/* (non-Javadoc)
	 * @see com.spring.boot.demo.mail.MailSender#send(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void send(String to, String subject, String body) {
		
		log.info("Sending SMTP mail to " + to);
		log.info("Subject: " + subject);
		log.info("Body: " + body);
		
	}

	/* (non-Javadoc)
	 * @see com.spring.boot.demo.mail.MailSender#send(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendMailToGmail(String to, String subject, String body) throws MessagingException {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		helper = new MimeMessageHelper(message);
		helper.setSubject(subject);
		helper.setText(body, true);
		helper.setTo(to);
		
		javaMailSender.send(message);
		
		log.info("Sending SMTP mail to " + to);
		log.info("Subject: " + subject);
		log.info("Body: " + body);
		
	}
}
