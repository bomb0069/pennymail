package com.odde.pennymail.service;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.stereotype.Service;

@Service
public class ContactService {
	ArrayList<String> emailList = new ArrayList<String>();

	public ArrayList<String> list() {
		Collections.sort(emailList);
		return emailList;
	}

	public void add(String email) {
		if (!emailList.contains(email)) {
				emailList.add(email);
		}
	}

}
