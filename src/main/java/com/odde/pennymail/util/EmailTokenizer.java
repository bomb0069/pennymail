package com.odde.pennymail.util;

import java.util.ArrayList;

public class EmailTokenizer {
	
	private String delims = ",|\\n";

	public String[] splitEmail(String mail){
		String[] mails = mail.split(delims);
		String mailWithTrim ="";
		ArrayList<String> recipientList = new ArrayList<String>();
		for (String email : mails){
			mailWithTrim = email.trim();
			if(!mailWithTrim.equals("") ){
			recipientList.add(mailWithTrim);
			}
		}
		return listToArray(recipientList);
	}

	private String[] listToArray(ArrayList<String> list) {
		String[] array  = new String[list.size()];
		array = list.toArray(array);
		return array;
	}
	
}
