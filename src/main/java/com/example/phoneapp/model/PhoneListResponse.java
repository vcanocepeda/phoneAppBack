package com.example.phoneapp.model;

import java.util.List;

/**
 * 
 * Phone Response
 * 
 * @author Vicente Cano Cepeda
 *
 */
public class PhoneListResponse {
	
	private List<PhoneDto> phoneList;
	
	public List<PhoneDto> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<PhoneDto> phoneList) {
		this.phoneList = phoneList;
	}

}
