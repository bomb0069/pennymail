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
		if (! isEmailValid(mail)) {
			model.put("errorMessage", "ePenny");
			model.put("mail", mail);
		} else {
			mailService.send(mail);
			MailRequest mailRequest = new MailRequest();
			mailRequest.setTopic("");
			mailRequest.setMessage("");
			model.put("mail", mailRequest);
		}
		return mav;
	}

	private boolean isEmailValid(MailRequest mail) {
		return MailValidator.validate(mail.getRecipients());
	}

	@RequestMapping(value = "/addrecipient", method = RequestMethod.GET)
	public String retrieveRecipient() {
		return "addrecipient";
	}

	@RequestMapping(value = "/addrecipient", method = RequestMethod.POST)
	public String addRecipient() {
		return "addrecipient";
	}

}
