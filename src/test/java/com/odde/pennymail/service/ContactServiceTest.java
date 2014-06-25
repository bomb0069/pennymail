package com.odde.pennymail.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ContactServiceTest {

	@Test
	public void getRecipientList() {
		ContactService contact = new ContactService();
		assertEquals(0, contact.list().size());
	}
	
	@Test
	public void shouldBeAbleToAddToTheContactList() {
		ContactService contact = new ContactService();
		contact.add("a@b.com");
		assertEquals("a@b.com", contact.list().get(0));
	}
}
