package com.odde.pennymail.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class ContactControllerTest {

	@Test
	public void listExistingContact() {
		ContactController controller = new ContactController();
		ModelAndView modelAndView = controller.list();
		assertEquals("contact", modelAndView.getViewName());		
	}

}
