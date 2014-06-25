package com.odde.pennymail.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ContactService {
	ArrayList<String> emaillist = new ArrayList<String>();

	public List list() {
		Collections.sort(emaillist);
		return emaillist;
	}

	public void add(String email) {
		if (!email.trim().equals("") && !emaillist.contains(email.trim())) {
				emaillist.add(email);
		}
	}

}
