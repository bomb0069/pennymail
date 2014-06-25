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
		List<String> errorList = (List<String>)mav.getModel().get("errorList");
		assertEquals(0, errorList.size());
		MailRequest mailRequest = (MailRequest)mav.getModel().get("mail");
		assertEquals("", mailRequest.getTopic());
		assertEquals("", mailRequest.getMessage());
		assertEquals("", mailRequest.getRecipients());
	}
	
	@Test
	public void testSendMailInvalidRecipients() throws EmailException {
		String invalidReceipients = "invalid@gmail";
		String topic              = "topic1";
		String message            = "message from penny";
		MailRequest mailReq = buildMailRequest(invalidReceipients,topic,message);
		ModelAndView mav = mailController.sendMail(mailReq);
		MailRequest mail = (MailRequest) mav.getModel().get("mail");
		
		assertEquals("sendmail", mav.getViewName());
		assertEquals(invalidReceipients,mail.getRecipients());
		assertEquals(topic,mail.getTopic());
		assertEquals(message,mail.getMessage());
		
		ArrayList<String> expectedErrorList = new ArrayList<String>();
		expectedErrorList.add("แสรดด e Penny โง่ " + invalidReceipients + " ใช้ไม่ได้");
		assertEquals(expectedErrorList, mav.getModel().get("errorList"));
	}
	
	@Test
	public void testSendMailToMultipleRecipients() throws EmailException {
		String multipleRecipients = "valid@gmail.com,valid2@gmail.com";
		String topic              = "topic1";
		String message            = "message from penny";
		MailRequest mailReq = buildMailRequest(multipleRecipients,topic,message);
		mailController.sendMail(mailReq);
		assertEquals(2,this.mailService.getSentCount());
		assertEquals("valid@gmail.com",this.mailService.getRecipientAtIndex(0));
		assertEquals("valid2@gmail.com",this.mailService.getRecipientAtIndex(1));
		
	}
	
	@Test
	public void testSendMailToMultipleRecipientsWithSomeInvalid() throws EmailException
	{
		String multipleRecipients = "valid@gmail.com,neung@.com,abc";
		String topic              = "topic1";
		String message            = "message from penny";
		MailRequest mailReq = buildMailRequest(multipleRecipients,topic,message);
		ModelAndView mav = mailController.sendMail(mailReq);
		assertEquals(1,this.mailService.getSentCount());
		assertEquals("valid@gmail.com",this.mailService.getRecipientAtIndex(0));

		assertEquals("sendmail", mav.getViewName());
		ArrayList<String> expectedErrorList = new ArrayList<String>();
		expectedErrorList.add("แสรดด e Penny โง่ neung@.com ใช้ไม่ได้");
		expectedErrorList.add("แสรดด e Penny โง่ abc ใช้ไม่ได้");
		assertEquals(expectedErrorList, mav.getModel().get("errorList"));
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
