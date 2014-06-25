package com.odde.pennymail.controller;

import static org.junit.Assert.assertEquals;

import org.apache.commons.mail.EmailException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.odde.pennymail.model.MailRequest;
import com.odde.pennymail.service.MailService.MailService;

public class MailControllerTest {
	MailController mailController;
	
	class MailServiceForTest extends MailService {
		
		@Override
		public void send(String recipient, String topic, String body) throws EmailException {
			// do nothing
		}
	}
	
	@Before
	public void setup()
	{
		mailController = new MailController();
		mailController.mailService = new MailServiceForTest();
	}
	
	@Test
	public void testComposeMailToReturnSendMailView() {
		
		assertEquals("The URL /sendmail should forward to sendmail view.", "sendmail",mailController.composeMail().getViewName());
	}
	
	@Test
	public void testSendMailSuccess() {
		MailRequest mailReq = buildMailRequest("neung@gmail.com","Topic1","message from penny");
		ModelAndView mav = mailController.sendMail(mailReq);
		
		assertEquals("After user click Send button, the page should forward to sendmail view.", "sendmail", mav.getViewName());
		assertEquals("When mail send success, there is no the error message", false, mav.getModel().containsKey("errorMessage"));
	}
	
	@Test
	public void testSendMailInvalidRecipientsWithErrorMessage() {
		MailRequest mailReq = buildMailRequest("neung@.com","Topic1","message from penny");
		ModelAndView mav = mailController.sendMail(mailReq);
		
		assertEquals("After user click Send button, the page should forward to sendmail view.", "sendmail", mav.getViewName());
		assertEquals("When mail send fail, the error message shoule be ePenny", "ePenny", mav.getModel().get("errorMessage"));
	}
	
	@Test
	public void testSendMailInvalidRecipientsRemainMailDetails() {
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
	public void testRetirveRecipientToReturnAddrecipientView() {
		MailController mailController = new MailController();
		assertEquals("The URL /addrecipient should forward to add recipient view.", "addrecipient", mailController.retrieveRecipient());
	}
	
	@Test
	public void testAddRecipientToReturnAddrecipientView() {
		MailController mailController = new MailController();
		assertEquals("After user click Add button, the page should forward to recipient view.", "addrecipient", mailController.addRecipient());
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
