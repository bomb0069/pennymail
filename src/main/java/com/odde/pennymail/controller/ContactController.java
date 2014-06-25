package com.odde.pennymail.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.odde.pennymail.service.ContactService;

@Controller
public class ContactController {

	private ContactService contactService;

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView list() {
		List contactList = contactService.list();

		ModelAndView modelAndView = new ModelAndView("contact");
		modelAndView.getModel().put("contactList", contactList);
		return modelAndView;
	}

	public void setContactService(ContactService service) {
		this.contactService = service;
	}

	public ModelAndView add(String email) {
		this.contactService.add(email);
		ModelAndView modelAndView = new ModelAndView("contact");
		List contactList = contactService.list();

		modelAndView.getModel().put("contactList", contactList);
		return modelAndView;
	}

}
