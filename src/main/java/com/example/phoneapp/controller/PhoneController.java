package com.example.phoneapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.phoneapp.model.CustomerOrderRequest;
import com.example.phoneapp.model.CustomerOrderResponse;
import com.example.phoneapp.model.PhoneDto;
import com.example.phoneapp.model.PhoneRequest;
import com.example.phoneapp.model.PhoneListResponse;
import com.example.phoneapp.model.PhoneResponse;
import com.example.phoneapp.service.PhoneService;

/**
 * 
 * Phone Rest Controller
 * 
 * @author Vicente Cano Cepeda
 *
 */
@RestController
public class PhoneController {
	
	@Autowired
	private PhoneService phoneService;
	
	/**
	 * Gets the phone list. 
	 * If you attach "fromFile" query string parameter set to true we will the get the list
	 * from a file inside the resources folder otherwise we will get it from a h2 embedded database.
	 * 
	 * @param {@link Boolean} fromFile 
	 *          
	 * @return {@link ResponseEntity<PhoneListResponse>} the response with the phone list requested.
	 *        
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/phone")
	public ResponseEntity<PhoneListResponse> listPhones(@RequestParam(value = "fromFile", defaultValue = "false") boolean fromFile) {
		PhoneListResponse response = new PhoneListResponse();
		if (fromFile) {
			response = phoneService.listPhonesFromFile();
		} else {
			response = phoneService.listPhonesFromEmbeddedDB();
		}
		
		if (null != response.getPhoneList() && !response.getPhoneList().isEmpty()) {			
			return new ResponseEntity<PhoneListResponse>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<PhoneListResponse>(HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * Gets a phone element 
	 * 
	 * @param {@link String} phoneId 
	 *          
	 * @return {@link ResponseEntity<PhoneDto>} the response with the phone element requested.
	 *        
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/phone/{phoneId}")
	public ResponseEntity<PhoneDto> getPhone(@PathVariable String phoneId) {
		
		PhoneDto phone = phoneService.getPhone(phoneId);
		
		if (null != phone && null != phone.getName()) {
			return new ResponseEntity<PhoneDto>(phone, HttpStatus.OK);
		} else {
			return new ResponseEntity<PhoneDto>(phone, HttpStatus.NO_CONTENT);
		}
		
	}
	
	/**
	 * Saves a phone. 
	 * 
	 * @param {@link PhoneRequest} phoneRequest 
	 *          
	 * @return {@link ResponseEntity<PhoneResponse>} the response with the phone saved.
	 *        
	 */
	@PostMapping(value = "/phone")
    public ResponseEntity<PhoneResponse> savePhone(@RequestBody PhoneRequest phoneRequest) {
		PhoneResponse response = phoneService.savePhone(phoneRequest);

		if (null != response.getPhone()) {			
			return new ResponseEntity<PhoneResponse>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<PhoneResponse>(HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * Sends an order with the customer information and the phone list.
	 * 
	 * @param {@link CustomerOrderRequest} customerOrderRequest 
	 *          
	 * @return {@link ResponseEntity<CustomerOrderResponse>} the response with the total price of the order.
	 *        
	 */	
	@PostMapping(value = "/customerOrder")
	public ResponseEntity<CustomerOrderResponse> customerOrder(@RequestBody CustomerOrderRequest customerOrderRequest) {
		
		CustomerOrderResponse response = phoneService.saveCustomerOrder(customerOrderRequest);
						
		return new ResponseEntity<CustomerOrderResponse>(response, HttpStatus.OK);
	}

}
