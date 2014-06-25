package com.odde.pennymail.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class MailValidatorTest {

	@Test
	public void validateValidEmailShouldReturnTrue() {
		assertTrue(MailValidator.validate("bomb@gmail.com"));
	}

	@Test
	public void validateNoDomainEmailShouldReturnFalse() {
		assertFalse(MailValidator.validate("Invalid.com"));
	}
	
	@Test
	public void validateAnotherValidEmailShouldReturnTrue() {
		assertTrue(MailValidator.validate("Max@gmail.com"));
	}
	
	@Test
	public void validateNoNameEmailShouldReturnFalse() {
		assertFalse(MailValidator.validate("@gmail.com"));
	}
	
	@Test
	public void validateDotNameEmailShouldReturnTrue() {
		assertTrue(MailValidator.validate("tor.dst@gmail.com"));
	}
	
	@Test
	public void validateUnderscoreNameEmailShouldReturnTrue() {
		assertTrue(MailValidator.validate("tor.dst_worldwide@gmail.com"));
	}
}
