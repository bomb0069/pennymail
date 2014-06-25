package com.odde.pennymail.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.odde.pennymail.service.ContactService;

public class ContactControllerTest {

	class FakeContactService extends ContactService {
		public ArrayList contactList = new ArrayList();

		public List list() {
			return contactList;
		}

		public void add(String email) {
			contactList.add(email);
		}
	}

	ContactController controller;
	FakeContactService service;

	@Before
	public void setUp() {
		controller = new ContactController();
		service = new FakeContactService();
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
		ArrayList oldContactList = (ArrayList) service.contactList.clone();
		ModelAndView modelAndView = controller.add("new@email.com");

		assertEquals("contact", modelAndView.getViewName());
		ArrayList newContactList = (ArrayList) modelAndView.getModel().get(
				"contactList");
		assertEquals(oldContactList.size() + 1, newContactList.size());

	}

}
