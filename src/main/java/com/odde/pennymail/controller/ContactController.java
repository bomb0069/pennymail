package com.odde.pennymail.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.odde.pennymail.service.ContactService;
import com.odde.pennymail.util.EmailTokenizer;
import com.odde.pennymail.util.MailValidator;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("contact");
		List contactList = contactService.list();
		modelAndView.getModel().put("contactList", contactList);
		return modelAndView;
	}

	public void setContactService(ContactService service) {
		this.contactService = service;
	}

	@RequestMapping(value = "/addrecipient", method = RequestMethod.POST)
	public ModelAndView add(String email) {
		EmailTokenizer token = new EmailTokenizer();
		ModelAndView modelAndView = list();
		if (email == null) {
			return modelAndView;
		}
		String[] mails = token.splitEmail(email);
		
		for (String mail: mails) {
			if(MailValidator.validate(mail)) {
				this.contactService.add(mail);
			} else {
				ArrayList<String> invalidList = new ArrayList<String>();
				invalidList.add(mail);
				modelAndView.getModel().put("invalidList", invalidList);
			}
		}
				
		return modelAndView;
	}

}
