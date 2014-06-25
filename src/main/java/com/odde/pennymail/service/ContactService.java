package com.odde.pennymail.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ContactService {
	ArrayList<String> emaillist = new ArrayList<String>();
	public List list() {
		return emaillist;
	}

	public void add(String email) {
		emaillist.add(email);
	}
}
