package com.odde.pennymail.service.MailService;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.odde.pennymail.model.MailRequest;

public class MailService {

	private void setAuthentication(Email email) {
		email.setAuthenticator(new DefaultAuthenticator("JUACOMPE.IG@gmail.com", "csd2014xitgmLwmp"));
		email.setSSLOnConnect(true);
	}

	public void send(String recipient, String topic, String body) throws EmailException {
		Email email = new SimpleEmail();
		setAuthentication(email);
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setFrom("JUACOMPE.IG@gmail.com");
		email.addTo(recipient);
		email.setSubject(topic);
		if(body == null || body.equals("")) {
			body = " ";
		}
		email.setMsg(body);
		sendMail(email);
	}

	protected void sendMail(Email email) throws EmailException {
		email.send();
	}

	public void send(MailRequest mail) throws EmailException {
		send(mail.getRecipients(), mail.getTopic(), mail.getMessage());
	}

}
