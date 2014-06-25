package com.odde.pennymail.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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

	ContactController controller = new ContactController();
	FakeContactService service = new FakeContactService();

	@Test
	public void listExistingContact() {
		controller.setContactService(service);
		ModelAndView modelAndView = controller.list();
		assertEquals("contact", modelAndView.getViewName());
		assertTrue(modelAndView.getModel().get("contactList") instanceof List);
	}

	@Test
	public void addEmail() {
		controller.setContactService(service);

		ArrayList oldContactList = (ArrayList) service.contactList.clone();
		ModelAndView modelAndView = controller.add("new@email.com");

		assertEquals("contact", modelAndView.getViewName());
		ArrayList newContactList = (ArrayList) modelAndView.getModel().get(
				"contactList");
		assertEquals(oldContactList.size() + 1, newContactList.size());

	}

}
