package com.example.phoneapp.model;

import java.util.List;

/**
 * 
 * Customer Order Request
 * 
 * @author Vicente Cano Cepeda
 *
 */
public class CustomerOrderRequest {
	
	private String name;
	private String surname;   
	private String email;
	private List<PhoneDto> phoneList;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<PhoneDto> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<PhoneDto> phoneList) {
		this.phoneList = phoneList;
	}
}
