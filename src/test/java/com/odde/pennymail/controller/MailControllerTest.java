package com.odde.pennymail.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class MailControllerTest {

	@Test
	public void testComposeMailToReturnSendMailView() {
		MailController mailController = new MailController();
		assertEquals("The URL /sendmail should forward to sendmail view.", "sendmail", mailController.composeMail());
	}
	
	@Test
	public void testSendMailToReturnSendMailView() {
		MailController mailController = new MailController();
		assertEquals("After user click Send button, the page should forward to sendmail view.", "sendmail", mailController.sendMail());
	}

	@Test
	public void testRetirveRecipientToReturnAddrecipientView() {
		MailController mailController = new MailController();
		assertEquals("The URL /addrecipient should forward to add recipient view.", "addrecipient", mailController.retrieveRecipient());
	}
	
	@Test
	public void testAddRecipientToReturnAddrecipientView() {
		MailController mailController = new MailController();
		assertEquals("After user click Add button, the page should forward to recipient view.", "addrecipient", mailController.addRecipient());
	}
}	
