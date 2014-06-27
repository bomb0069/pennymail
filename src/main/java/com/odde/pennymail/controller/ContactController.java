package com.odde.pennymail.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.odde.pennymail.service.ContactService;
import com.odde.pennymail.util.MailValidator;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("contact");
		List<String> contactList = contactService.list();
		modelAndView.getModel().put("contactList", contactList);
		return modelAndView;
	}

	public void setContactService(ContactService service) {
		this.contactService = service;
	}

	@RequestMapping(value = "/addrecipient", method = RequestMethod.POST)
	public ModelAndView add(@RequestParam(value = "addRecipients") String emails) {
		ArrayList<String> invalidList = addToContactList(emails);
		return addInvalidListToModelAndView(invalidList);
	}

	private ArrayList<String> addToContactList(String emails) {
		ArrayList<String> invalidList = new ArrayList<String>();
		String delims = ",";
		String[] mails = emails.split(delims);
		for (String mail : mails) {
			mail = mail.trim();
			if (MailValidator.validate(mail)) {
				this.contactService.add(mail);
			} else {
				invalidList.add(mail);
			}
		}
		return invalidList;
	}

	private ModelAndView addInvalidListToModelAndView(ArrayList<String> invalidList) {
		ModelAndView modelAndView = list();
		modelAndView.getModel().put("invalidList", invalidList);
		return modelAndView;
	}

	@RequestMapping(value = "/addattribute", method = RequestMethod.POST)
	public ModelAndView addAttributeToContact(
			@RequestParam(value = "email") String email, 
			@RequestParam(value = "attribute") String attribute) {
		
		ModelAndView modelAndView = new ModelAndView("contact");
		
		return modelAndView;
	}

}
