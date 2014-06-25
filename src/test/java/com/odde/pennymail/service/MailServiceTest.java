package com.odde.pennymail.service;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.junit.Test;

import com.odde.pennymail.service.MailService.MailService;

public class MailServiceTest {
	
	class MailServiceForTest extends MailService {
		private Email savedEmail = null;
		
		@Override
		protected
		void sendMail(Email email) throws EmailException {
			email.buildMimeMessage();
			this.savedEmail = email;
			
		}

		public Email getSavedEmail() {
			return savedEmail;
		}
	}

	@Test
	public void recipientSholdBeSetAsToAddress() throws EmailException {
		MailServiceForTest mailService = new MailServiceForTest();
		mailService.send("bomb@gmail.com", "New Gadgets", "Sales 50% Off");
		assertEquals("bomb@gmail.com", mailService.getSavedEmail().getToAddresses().get(0).toString());
	}
	
	@Test
	public void topicSholdBeSetAsSubject() throws EmailException {
		MailServiceForTest mailService = new MailServiceForTest();
		mailService.send("bomb@gmail.com", "New Gadgets", "Sales 50% Off");
		assertEquals("New Gadgets", mailService.getSavedEmail().getSubject());
	}

	@Test
	public void bodySholdBeSetAsMessage() throws EmailException, IOException, MessagingException {
		MailServiceForTest mailService = new MailServiceForTest();;
		mailService.send("bomb@gmail.com", "New Gadgets", "Sales 0% Off");
		assertEquals("Sales 0% Off", mailService.getSavedEmail().getMimeMessage().getContent());
	}
}
