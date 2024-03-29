package com.odde.pennymail.model;

import com.odde.pennymail.util.EmailTokenizer;

public class MailRequest {
	private String recipients;
	private String topic;
	private String message;
	
	public MailRequest() {
		this.topic = "";
		this.message = "";
		this.recipients = "";
	}

	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getRecipientsList() {
		EmailTokenizer emailTokenizer = new EmailTokenizer();		
		return emailTokenizer.splitEmail(recipients);
	}


}
