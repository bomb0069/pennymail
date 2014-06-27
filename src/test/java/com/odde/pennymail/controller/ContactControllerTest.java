package com.odde.pennymail.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.odde.pennymail.service.ContactService;

public class ContactControllerTest {
	ContactController controller;
	ContactService service;

	@Before
	public void setUp() {
		controller = new ContactController();
		service = new ContactService();
		controller.setContactService(service);
	}

	@Test
	public void listExistingContact() {
		ModelAndView modelAndView = controller.list();
		assertEquals("contact", modelAndView.getViewName());
		assertTrue(modelAndView.getModel().get("contactList") instanceof List);
	}

	@Test
	public void addEmail() {
		ArrayList<String> contactList = (ArrayList<String>) service.list();
		int expectedContactCount = contactList.size() + 1;
		ModelAndView modelAndView = controller.add("new@email.com");

		assertEquals("contact", modelAndView.getViewName());
		contactList = (ArrayList<String>) service.list();
		assertEquals(expectedContactCount, contactList.size());

	}

	@Test
	public void getErrorForAddingInvalidEmail() {
		ModelAndView modelAndView = controller.add("newemail.com");
		ArrayList<?> invalidList = (ArrayList<?>)modelAndView.getModel().get("invalidList");

		assertEquals("contact", modelAndView.getViewName());
		assertEquals("newemail.com",invalidList.get(0));
	}

	@Test
	public void addMultipleEmail() {
		ArrayList<String> contactList = (ArrayList<String>) service.list();
		int expectedContactCount = contactList.size() + 2;
		ModelAndView modelAndView = controller
				.add("new@email.com,nn@email.com");

		assertEquals("contact", modelAndView.getViewName());
		contactList = (ArrayList<String>) service.list();
		assertEquals(expectedContactCount, contactList.size());
	}
	

}
