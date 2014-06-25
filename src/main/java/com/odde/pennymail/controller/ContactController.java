package com.odde.pennymail.controller;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.odde.pennymail.service.ContactService;

public class ContactController {

	private ContactService contactService;

	public ModelAndView list() {
		List contactList = contactService.list();

		ModelAndView modelAndView = new ModelAndView("contact");
		modelAndView.getModel().put("contactList", contactList);
		return modelAndView;
	}

	public void setContactService(ContactService service) {
		this.contactService = service;
	}

}
