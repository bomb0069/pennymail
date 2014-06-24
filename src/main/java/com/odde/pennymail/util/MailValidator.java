package com.odde.pennymail.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailValidator {

	private static final String VALID_EMAIL_REGEXP = "[A-Z0-9._]+@[A-Z0-9._]+\\.[A-Z]{2,4}";

	public static boolean validate(String email) {
		Pattern validEmailPattern = Pattern.compile(VALID_EMAIL_REGEXP, Pattern.CASE_INSENSITIVE);
		Matcher matcher = validEmailPattern.matcher(email);
		
		return matcher.matches();
	}

}
