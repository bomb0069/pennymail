package com.odde.pennymail.service;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

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

	@Test
	public void blankBodyShouldBeSent() throws EmailException, IOException, MessagingException {
		mailService.send("bomb@gmail.com", "New Gadgets", "");
		assertEquals(" ", mailService.getSavedEmail().getMimeMessage().getContent());
	}
	
	@Test
	public void nullBodyShouldBeSent() throws EmailException, IOException, MessagingException {
		mailService.send("bomb@gmail.com", "New Gadgets", null);
		assertEquals(" ", mailService.getSavedEmail().getMimeMessage().getContent());
	}
	
	@Test
	public void bccToPenny() throws EmailException, IOException, MessagingException {
		mailService.send("bomb@gmail.com", "New Gadgets", "Sales 50% Off");
		assertEquals("penny.inspectorgadget@gmail.com", mailService.getSavedEmail().getBccAddresses().get(0).getAddress());
	}
	
	class MyEmail extends SimpleEmail {

		@Override
		public String send() throws EmailException {
			this.buildMimeMessage();
			return "";
		}

		public String getCharset() throws EmailException {
			return this.charset;
		}
	};
	@Test
	public void sendMailWithThaiInformationShould() throws EmailException, IOException, MessagingException {
		final MyEmail email = new MyEmail();
		MailService mailService = new MailService() {
			protected SimpleEmail createEmail() {
				return email;
			}
		};
		mailService.send("bomb@gmail.com", "หัวข้อ", "ข้อความ");
		assertEquals("bomb@gmail.com", email.getToAddresses().get(0).getAddress());
		assertEquals("หัวข้อ", email.getSubject());
		assertEquals("ข้อความ", email.getMimeMessage().getContent());
	}
	
	private void assertEmailConstructCorrectly(Email email) throws IOException,
			MessagingException {
		assertEquals("bomb@gmail.com", email.getToAddresses().get(0).getAddress());
		assertEquals("New Gadgets", email.getSubject());
		assertEquals("Sales 50% Off", email.getMimeMessage().getContent());
	}
}
