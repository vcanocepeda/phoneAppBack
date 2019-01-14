package com.example.phoneapp.service;

import com.example.phoneapp.exception.PhoneException;
import com.example.phoneapp.model.CustomerOrderRequest;
import com.example.phoneapp.model.CustomerOrderResponse;
import com.example.phoneapp.model.PhoneDto;
import com.example.phoneapp.model.PhoneRequest;
import com.example.phoneapp.model.PhoneListResponse;
import com.example.phoneapp.model.PhoneResponse;

/**
 * 
 * Phone Service
 * 
 * @author Vicente Cano Cepeda
 *
 */
public interface PhoneService {
	/**
	 * Gets the phone list from a Json File
	 *
	 * @return {@link PhoneListResponse} the phone list
	 */
	public PhoneListResponse listPhonesFromFile();
	
	/**
	 * Gets the phone list from a H2 Embedded DataBase
	 *
	 * @return {@link PhoneListResponse} the phone list
	 */
	public PhoneListResponse listPhonesFromEmbeddedDB();
	
	/**
	 * Gets the phone element
	 *
	 * @param {@link String} id of the phone to get
	 * 
	 * @return {@link PhoneDto} the phone element
	 */
	public PhoneDto getPhone(String id);
	
	/**
	 * Saves a new phone in the DataBase
	 *
	 * @param {@link PhoneRequest} the phone to save
	 * 
	 * @return {@link PhoneResponse} the phone saved
	 */
	public PhoneResponse savePhone(PhoneRequest phoneRequest);
	
	/**
	 * Saves the customer order with the phone list
	 *
	 * @param {@link CustomerOrderRequest} the customer order
	 * 
	 * @return {@link CustomerOrderResponse} the order total price
	 */
	public CustomerOrderResponse saveCustomerOrder(CustomerOrderRequest request) throws PhoneException;
}
