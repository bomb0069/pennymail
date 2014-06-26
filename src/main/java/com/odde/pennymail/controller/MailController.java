package com.odde.pennymail.controller;

import java.util.ArrayList;
import java.util.List;
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
		List<String> errorList = new ArrayList<String>();
		mav.setViewName("sendmail");
		Map<String, Object> model = mav.getModel();
		
		for (String recipient : mail.getRecipientsList()) {
			if (!isEmailValid(recipient)) {
				errorList.add("แสรดด e Penny โง่ " + recipient + " ใช้ไม่ได้");
				model.put("mail", mail);
			} else {
				mailService.send(recipient, mail.getTopic(), mail.getMessage());
				model.put("mail", new MailRequest());
			}
		}
		model.put("errorList", errorList);
		return mav;
	}

	private boolean isEmailValid(String mail) {
		return MailValidator.validate(mail);
	}

}
