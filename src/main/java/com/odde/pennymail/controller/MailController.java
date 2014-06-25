package com.odde.pennymail.controller;

import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.odde.pennymail.model.MailRequest;
import com.odde.pennymail.service.MailService.MailService;
import com.odde.pennymail.util.EmailTokenizer;
import com.odde.pennymail.util.MailValidator;

@Controller
public class MailController {
	MailService mailService = new MailService();

	@RequestMapping(value = "/sendmail", method = RequestMethod.GET)
	public ModelAndView composeMail() {
		MailRequest mailreq = new MailRequest();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sendmail");
		mav.getModel().put("mail", mailreq);
		return mav;
	}

	@RequestMapping(value = "/sendmail", method = RequestMethod.POST)
	public ModelAndView sendMail(@ModelAttribute("mail") MailRequest mail)
			throws EmailException {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sendmail");
		Map<String, Object> model = mav.getModel();
		
		EmailTokenizer emailTokenizer = new EmailTokenizer();
		
		for (String recipient : emailTokenizer.splitEmail(mail.getRecipients())) {
			if (!isEmailValid(recipient)) {
				model.put("errorMessage", "ePenny");
				model.put("mail", mail);
			} else {
				mailService.send(recipient, mail.getTopic(), mail.getMessage());
				model.put("mail", new MailRequest());
			}
		}
		return mav;
	}

	private boolean isEmailValid(String mail) {
		return MailValidator.validate(mail);
	}

}
