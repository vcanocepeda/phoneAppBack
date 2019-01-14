package com.example.phoneapp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.phoneapp.entity.CustomerOrder;
import com.example.phoneapp.entity.Phone;
import com.example.phoneapp.exception.PhoneException;
import com.example.phoneapp.mapper.PhoneMapper;
import com.example.phoneapp.model.CustomerOrderRequest;
import com.example.phoneapp.model.CustomerOrderResponse;
import com.example.phoneapp.model.PhoneDto;
import com.example.phoneapp.model.PhoneRequest;
import com.example.phoneapp.model.PhoneListResponse;
import com.example.phoneapp.model.PhoneResponse;
import com.example.phoneapp.repository.CustomerOrderRepository;
import com.example.phoneapp.repository.PhoneRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * Phone Service Implementation
 * 
 * @author Vicente Cano Cepeda
 *
 */
@Service
public class PhoneServiceImpl implements PhoneService {
	
	@Value("${config.pathtoJson}")
	private String pathtoJson;
	
	private static final Logger log = LoggerFactory.getLogger(PhoneServiceImpl.class);
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@Autowired
	private CustomerOrderRepository customerOrderRepository;
	
	@Autowired
	private PhoneMapper phoneMapper;
	
	@Override
	public PhoneListResponse listPhonesFromFile() {

		ObjectMapper objectMapper = new ObjectMapper();
		PhoneListResponse response = new PhoneListResponse();
		try {		
			String json = new String(Files.readAllBytes(Paths.get(this.pathtoJson)));
			response.setPhoneList(objectMapper.readValue(json, new TypeReference<List<PhoneDto>>(){}));
		} catch (IOException e) {
			log.error("Error we are not able to get the resource in :" + this.pathtoJson);
		}
		return response;
	}
	
	@Override
	public PhoneListResponse listPhonesFromEmbeddedDB() {
		PhoneListResponse response = new PhoneListResponse();
		Iterable<Phone> phoneIterable = phoneRepository.findAll();
		
		List<Phone> phoneList = new ArrayList<Phone>();
		for (Phone phone : phoneIterable) {
			phoneList.add(phone);
		}
		
		response.setPhoneList(phoneMapper.createPhoneDtoList(phoneList));
		return response;	
	}
	
	@Override
	public PhoneDto getPhone(String id) {
		Optional<Phone> phone = phoneRepository.findById(1L);
		PhoneDto phoneDto = new PhoneDto();
		if (phone.isPresent()) {
			phoneDto = phoneMapper.createPhoneDto(phone.get());
		}
		return phoneDto;	
	}
	
	@Override 
	public PhoneResponse savePhone(PhoneRequest phoneRequest) {
		PhoneResponse phoneResponse = new PhoneResponse();
		Phone phoneToSave = phoneRequest.getPhone() != null ? 
				phoneMapper.createPhone(phoneRequest.getPhone()) : null;
		
		Phone phoneSaved = phoneRepository.save(phoneToSave);	
		
		phoneResponse.setPhone(phoneMapper.createPhoneDto(phoneSaved));
		return phoneResponse;	
	}
	
	@Override
	public CustomerOrderResponse saveCustomerOrder(CustomerOrderRequest customerOrderRequest) throws PhoneException {
		
		List<Phone> phoneList = new ArrayList<Phone>();
		int totalPriceOrder = 0;
		for (PhoneDto phoneDto : customerOrderRequest.getPhoneList()) {			
			totalPriceOrder += phoneDto.getPrice();
			Optional<Phone> phone = phoneRepository.findById(phoneDto.getId());
			
			if (phone.isPresent()) {
				phoneList.add(phone.get());
			} else  {
				throw new PhoneException("The phone id: " + phoneDto.getId() + " cannot be found in the Database");				
			}
		}	
		CustomerOrderResponse customerOrderResponse = new CustomerOrderResponse();
		customerOrderResponse.setOrderPrice(totalPriceOrder);
		
		CustomerOrder customerOrder = phoneMapper.createCustomerOrder(customerOrderRequest, phoneList);
		customerOrderRepository.save(customerOrder);
					
		// we log the final order
		log.info("-------------------------------");
		log.info("customerOrder: " + customerOrder.toString());
		for (Phone phone : customerOrder.getPhoneList()) {
			log.info("PhonesOrder: " + phone.toString());
		}
		log.info("total price order: " + totalPriceOrder);
		
		return customerOrderResponse;	
	}
}
