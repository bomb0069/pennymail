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
	MailServiceForTest mailService = new MailServiceForTest();

	@Test
	public void recipientSholdBeSetAsToAddress() throws EmailException, IOException, MessagingException {
		mailService.send("bomb@gmail.com", "New Gadgets", "Sales 50% Off");
		assertEmailConstructCorrectly(mailService.getSavedEmail());
	}

	private void assertEmailConstructCorrectly(Email email) throws IOException,
			MessagingException {
		assertEquals("bomb@gmail.com", email.getToAddresses().get(0).toString());
		assertEquals("New Gadgets", email.getSubject());
		assertEquals("Sales 50% Off", email.getMimeMessage().getContent());
	}
	
}
