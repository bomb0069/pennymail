package com.odde.pennymail.util;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class EmailTokenizerTest {
		EmailTokenizer testMail = new EmailTokenizer();
	
	@Test
	public void whiteSpaceBetweenEmail() {
		String[] expected = {"a@b.com","aa@bb.com","aaa@bbb.com"};
		String[] A = testMail.splitEmail("a@b.com , aa@bb.com , aaa@bbb.com ");
		assertArrayEquals(expected, A);
	}
	
	@Test
	public void nullValueBetweenEmail() {
		String[] expected = {"a@b.com","aa@bb.com","aaa@bbb.com"};
		String[] A = testMail.splitEmail("a@b.com , aa@bb.com ,  , aaa@bbb.com ");
		assertArrayEquals(expected, A);
	}
	
	@Test
	public void nullInput() {
		String[] expected = {};
		String[] A = testMail.splitEmail("");
		assertArrayEquals(expected, A);
	}	

}