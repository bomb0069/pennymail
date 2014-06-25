package com.odde.pennymail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.odde.pennymail.service.ContactService;

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
		this.contactService.add(email);
		return list();
	}

}
