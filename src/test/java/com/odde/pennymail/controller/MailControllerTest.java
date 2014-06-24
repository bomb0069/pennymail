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

}
