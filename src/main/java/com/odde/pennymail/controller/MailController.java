package com.odde.pennymail.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MailController {
	
	@RequestMapping(value = "/sendmail", method = RequestMethod.GET)
	public String composeMail() {
		return "sendmail";
	}
	
	@RequestMapping(value = "/sendmail", method = RequestMethod.POST)
	public String sendMail(){
		return "sendmail";
	}	
	
	@RequestMapping(value = "/addrecipient", method = RequestMethod.GET)
	public String retrieveRecipient(){
		return "addrecipient";
	}	

	@RequestMapping(value = "/addrecipient", method = RequestMethod.POST)
	public String addRecipient(){
		return "addrecipient";
	}	

}
