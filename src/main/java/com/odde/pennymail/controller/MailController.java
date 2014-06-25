package com.odde.pennymail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.odde.pennymail.model.MailRequest;
import com.odde.pennymail.util.MailValidator;

@Controller
public class MailController {

	@RequestMapping(value = "/sendmail", method = RequestMethod.GET)
	public ModelAndView composeMail() {
		MailRequest mailreq = new MailRequest();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sendmail");
		mav.getModel().put("mail",mailreq);
		return mav;
	}

	@RequestMapping(value = "/sendmail", method = RequestMethod.POST)
	public ModelAndView sendMail(@ModelAttribute("mail") MailRequest mail) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sendmail");
		if (!MailValidator.validate(mail.getRecipients())){ 
			mav.getModel().put("errorMessage", "ePenny");
			mav.getModel().put("mail",mail);
		}else{
			mav.getModel().put("mail",new MailRequest());
			mav.getModel().remove("errorMessage");
		}
		return mav;
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
