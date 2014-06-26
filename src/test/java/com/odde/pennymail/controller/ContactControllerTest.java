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
		ArrayList<String> oldContactList = (ArrayList<String>) service.list()
				.clone();
		ModelAndView modelAndView = controller.add("new@email.com");

		assertEquals("contact", modelAndView.getViewName());
		ArrayList<String> newContactList = (ArrayList<String>) modelAndView
				.getModel().get("contactList");
		assertEquals(oldContactList.size() + 1, newContactList.size());

	}

	@Test
	public void getErrorForAddingInvalidEmail() {
		ModelAndView modelAndView = controller.add("newemail.com");

		assertEquals("contact", modelAndView.getViewName());
		assertEquals("newemail.com",
				((List) modelAndView.getModel().get("invalidList")).get(0));
	}

	@Test
	public void addMultipleEmail() {
		ArrayList<String> oldContactList = (ArrayList<String>) service.list()
				.clone();
		ModelAndView modelAndView = controller
				.add("new@email.com,nn@email.com");

		ArrayList<String> newContactList = (ArrayList<String>) modelAndView
				.getModel().get("contactList");
		assertEquals(oldContactList.size() + 2, newContactList.size());
	}

}
