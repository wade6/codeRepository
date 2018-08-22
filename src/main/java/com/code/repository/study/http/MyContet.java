package com.code.repository.study.http;

import java.util.ArrayList;
import java.util.List;

public class MyContet {

	String name;
	String contact;
	String web;
	String address;
	String mail;
	int i;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	
	public static void main(String[] args) {
		List<String> ss = new ArrayList<String>();
		ss.add("qw");
		ss.add("12");
		
		System.out.println(ss.toString());
	}
	
}

