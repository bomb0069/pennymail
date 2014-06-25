package com.odde.pennymail.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ContactServiceTest {

	@Test
	public void getZeroRecipientList() {
		ContactService contact = new ContactService();
		assertEquals(0, contact.list().size());
	}
	
	@Test
	public void shouldBeAbleToAddToTheContactList() {
		ContactService contact = new ContactService();
		contact.add("a@mail.com");
		assertEquals("a@mail.com", contact.list().get(0));
	}
	
	@Test
	public void shouldBeAbleToAddMultipleContacts()
	{
		ContactService contact = new ContactService();
		contact.add("a@mail.com");
		contact.add("b@mail.com");
		contact.add("c@mail.com");
		assertEquals("cannot add multiple contact",3,contact.list().size());
	}
	
}
