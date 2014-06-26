package com.odde.pennymail.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

@Service
public class ContactService {
	TreeMap<String,String> emailMap = new TreeMap<String,String>();

	public ArrayList<String> list() {
		return new ArrayList<String>(emailMap.keySet());
	}

	public void add(String email) {
		if (!emailMap.keySet().contains(email)) {
				emailMap.put(email,"");
		}
	}

	public TreeMap<String, String> attributeMap() {
		return emailMap;
	}

}
