package com.mani.util;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String to,String subject,String mailBody)
	{
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		boolean status=false;
		try {
			
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(mailBody,true);
			mailSender.send(mimeMessage);
			status=true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return status;
	}
}
