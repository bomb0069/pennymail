package com.odde.pennymail.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ContactService {
	ArrayList<String> emailList = new ArrayList<String>();

	public ArrayList<String> list() {
		Collections.sort(emailList);
		return emailList;
	}

	public void add(String email) {
		if (null != email &&!email.trim().equals("") && !emailList.contains(email.trim())) {
				emailList.add(email);
		}
	}

}
