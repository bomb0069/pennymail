package com.odde.pennymail.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.odde.pennymail.model.MailRequest;
import com.odde.pennymail.service.MailService.MailService;

public class MailControllerTest {
	MailController mailController;
	MailServiceForTest mailService;
	class MailServiceForTest extends MailService {
		List<String> sentRecipientsList = new ArrayList<String>();
		
		public boolean isSent() {
			return getSentCount() > 0;
		}
		@Override
		public void send(String recipient, String topic, String body) throws EmailException {
			sentRecipientsList.add(recipient);
		}
		public int getSentCount() {
			return sentRecipientsList.size();
		}
		public String getRecipientAtIndex(int i)
		{
			return sentRecipientsList.get(i);
		}
	}
	
	@Before
	public void setup()
	{
		mailController = new MailController();
		mailService = new MailServiceForTest();
		mailController.mailService = mailService;
	}
	
	@Test
	public void testComposeMailToReturnSendMailView() {
		
		assertEquals("The URL /sendmail should forward to sendmail view.", "sendmail",mailController.composeMail().getViewName());
	}
	
	@Test
	public void testSendMailSuccess() throws EmailException {
		MailRequest mailReq = buildMailRequest("neung@gmail.com","Topic1","message from penny");
		ModelAndView mav = mailController.sendMail(mailReq);
		
		assertEquals(true, mailService.isSent());
		assertEquals("sendmail", mav.getViewName());
		assertEquals(false, mav.getModel().containsKey("errorMessage"));
		MailRequest mailRequest = (MailRequest)mav.getModel().get("mail");
		assertEquals("", mailRequest.getTopic());
		assertEquals("", mailRequest.getMessage());
		assertEquals("", mailRequest.getRecipients());
	}
	
	@Test
	public void testSendMailInvalidRecipientsWithErrorMessage() throws EmailException {
		MailRequest mailReq = buildMailRequest("neung@.com","Topic1","message from penny");
		ModelAndView mav = mailController.sendMail(mailReq);
		
		assertEquals("After user click Send button, the page should forward to sendmail view.", "sendmail", mav.getViewName());
		assertEquals("When mail send fail, the error message shoule be ePenny", "ePenny", mav.getModel().get("errorMessage"));
	}
	
	@Test
	public void testSendMailInvalidRecipientsRemainMailDetails() throws EmailException {
		String invalidReceipients = "invalid@gmail";
		String topic              = "topic1";
		String message            = "message from penny";
		MailRequest mailReq = buildMailRequest(invalidReceipients,topic,message);
		ModelAndView mav = mailController.sendMail(mailReq);
		MailRequest mail = (MailRequest) mav.getModel().get("mail");
		
		assertEquals("After user click Send button, the page should forward to sendmail view.", "sendmail", mav.getViewName());
		assertEquals("When mail send fail, Recipients should be "+invalidReceipients, invalidReceipients,mail.getRecipients());
		assertEquals("When mail send fail, topic should be "+topic, topic,mail.getTopic());
		assertEquals("When mail send fail, message should be "+message, message,mail.getMessage());
	}
	
	
	@Test
	public void testSendMailToMultipleRecipients() throws EmailException {
		String multipleRecipients = "valid@gmail.com,valid2@gmail.com";
		String topic              = "topic1";
		String message            = "message from penny";
		MailRequest mailReq = buildMailRequest(multipleRecipients,topic,message);
		ModelAndView mav = mailController.sendMail(mailReq);
		assertEquals(2,this.mailService.getSentCount());
		assertEquals("valid@gmail.com",this.mailService.getRecipientAtIndex(0));
		assertEquals("valid2@gmail.com",this.mailService.getRecipientAtIndex(1));
		
	}
	
	private MailRequest buildMailRequest(String recipients,String topic,String message)
	{
		MailRequest mailReq = new MailRequest();
		mailReq.setRecipients(recipients);
		mailReq.setTopic(topic);
		mailReq.setMessage(message);
		return mailReq;
	}
	
}	
