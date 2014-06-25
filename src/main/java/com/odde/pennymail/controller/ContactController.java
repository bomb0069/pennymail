package com.odde.pennymail.controller;

import org.springframework.web.servlet.ModelAndView;

public class ContactController {

	public ModelAndView list() {
		return new ModelAndView("contact");
	}

}
