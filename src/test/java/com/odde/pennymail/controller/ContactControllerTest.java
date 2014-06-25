package com.odde.pennymail.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.odde.pennymail.service.ContactService;

public class ContactControllerTest {

	class FakeContactService extends ContactService {
		public List list() {
			return new ArrayList();
		}
	}

	@Test
	public void listExistingContact() {
		ContactController controller = new ContactController();
		FakeContactService service = new FakeContactService();

		controller.setContactService(service);
		ModelAndView modelAndView = controller.list();
		assertEquals("contact", modelAndView.getViewName());
		assertTrue(modelAndView.getModel().get("contactList") instanceof List);
	}

}
