package com.odde.pennymail.service;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ContactServiceTest {
	ContactService contact;
	
	@Before
	public void setUp() {
		contact = new ContactService();
	}
	
	@Test
	public void shouldBeAbleToAddToTheContactList() {
		contact.add("a@mail.com");
		assertEquals("a@mail.com", contact.list().get(0));
	}
	
	@Test
	public void getZeroRecipientList() {
		assertEquals(0, contact.list().size());
	}	

	@Test
	public void shouldBeAbleToAddMultipleContacts()
	{
		contact.add("a@mail.com");
		contact.add("b@mail.com");
		contact.add("c@mail.com");
		assertEquals("cannot add multiple contact",3,contact.list().size());
	}
	
	@Test
	public void contactListShouldBeSorted() {
		List<String> expected = new ArrayList<String>();
		expected.add("a@mail.com");
		expected.add("b@mail.com");
		expected.add("c@mail.com");
		
		contact.add("c@mail.com");
		contact.add("b@mail.com");
		contact.add("a@mail.com");
		assertEquals(expected, contact.list());
	}
	
	@Test
	public void duplicateContacts()
	{
		contact.add("a@mail.com");
		contact.add("a@mail.com");
		contact.add("b@mail.com");
		assertEquals("cannot add multiple contact",2,contact.list().size());
	}
}
