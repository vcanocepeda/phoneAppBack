package com.example.phoneapp.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.phoneapp.entity.CustomerOrder;
import com.example.phoneapp.entity.Phone;
import com.example.phoneapp.model.CustomerOrderRequest;
import com.example.phoneapp.model.PhoneDto;

/**
 * PhoneMapper.java
 *
 * @author Vicente Cano Cepeda
 *
 */
@Component
public class PhoneMapper {
	
	/**
	 * Create a phone entity object from an model object
	 *
	 * @param phoneList {@link PhoneDto}
	 * 
	 * @return {@link Phone}
	 */
	public Phone createPhone(PhoneDto phoneDto) {

		Phone phone = new Phone();
		phone.setId(phoneDto.getId());
		phone.setName(phoneDto.getName());
		phone.setDescription(phoneDto.getDescription());
		phone.setImage(phoneDto.getImage());
		phone.setPrice(phoneDto.getPrice());

		return phone;		
	}
	
	/**
	 * Create a phone model object from an entity object
	 *
	 * @param phoneList {@link Phone}
	 * 
	 * @return {@link PhoneDto}
	 */
	public PhoneDto createPhoneDto(Phone phone) {

		PhoneDto phoneDto = new PhoneDto();
		phoneDto.setId(phone.getId());
		phoneDto.setName(phone.getName());
		phoneDto.setDescription(phone.getDescription());
		phoneDto.setImage(phone.getImage());
		phoneDto.setPrice(phone.getPrice());

		return phoneDto;		
	}
	
	/**
	 * Create a phone list model object from an entity list object
	 *
	 * @param phoneList {@link List<Phone>}
	 * 
	 * @return {@link List<PhoneDto>}
	 */
	public List<PhoneDto> createPhoneDtoList(List<Phone> phoneList) {
		List<PhoneDto> phoneDtoList = new ArrayList<PhoneDto>();
		for (Phone phone : phoneList) {
			PhoneDto phoneDto = createPhoneDto(phone);
			phoneDtoList.add(phoneDto);
		}
		return phoneDtoList;		
	}
	
	/**
	 * Creates a customer order entity object from a customer order request model object and a list of Phone entity
	 *
	 * @param customerOrderRequest {@link CustomerOrderRequest}
	 * @param phoneList {@link List<Phone>}
	 * 
	 * @return {@link CustomerOrder}
	 */
	public CustomerOrder createCustomerOrder(CustomerOrderRequest customerOrderRequest, List<Phone> phoneList) {
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setName(customerOrderRequest.getName());
		customerOrder.setSurname(customerOrderRequest.getSurname());
		customerOrder.setEmail(customerOrderRequest.getEmail());
		
		customerOrder.setPhoneList(phoneList);
		return customerOrder;		
	}

}
