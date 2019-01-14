package com.example.phoneapp.mapper;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.phoneapp.entity.CustomerOrder;
import com.example.phoneapp.entity.Phone;
import com.example.phoneapp.model.CustomerOrderRequest;
import com.example.phoneapp.model.PhoneDto;

@RunWith(MockitoJUnitRunner.class)
public class PhoneMapperTest {
	
	@InjectMocks
	@Resource
	private PhoneMapper mapper = new PhoneMapper();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	//TODO mapper.createPhone(PhoneDto phoneDto) -- createPhoneDto(Phone phone)

	@Test
	public void createPhoneDtoListTest_OK() {
		List<Phone> phoneList = new ArrayList<Phone>();		
		phoneList.add(new Phone(1, "Samsung", "Samsung Galaxy 7", "/src/main/resources/galaxy7-img.jpg", 620));
		phoneList.add(new Phone(2, "Iphone", "Iphone X", "/src/main/resources/iphonex-img.jpg", 860));
		List<PhoneDto> phoneDtoList = mapper.createPhoneDtoList(phoneList);
		
		assertThat(phoneDtoList.get(0).getId(), is(1L));
		assertThat(phoneDtoList.get(0).getName(), is("Samsung"));
		assertThat(phoneDtoList.get(0).getDescription(), is("Samsung Galaxy 7"));
		assertThat(phoneDtoList.get(0).getImage(), is("/src/main/resources/galaxy7-img.jpg"));
		assertThat(phoneDtoList.get(0).getPrice(), is(620));
		assertThat(phoneDtoList.get(1).getId(), is(2L));
		assertThat(phoneDtoList.get(1).getName(), is("Iphone"));
		assertThat(phoneDtoList.get(1).getDescription(), is("Iphone X"));
		assertThat(phoneDtoList.get(1).getImage(), is("/src/main/resources/iphonex-img.jpg"));
		assertThat(phoneDtoList.get(1).getPrice(), is(860));
	}
	
	@Test
	public void createCustomerOrderTest_OK() {
		
		CustomerOrderRequest customerOrderRequest = new CustomerOrderRequest();
		customerOrderRequest.setName("Alberto");
		customerOrderRequest.setSurname("Marquina");
		customerOrderRequest.setEmail("albertoMarquina@gmail.com");
		List<Phone> phoneList = new ArrayList<Phone>();		
		phoneList.add(new Phone("Samsung", "Samsung Galaxy 7", "/src/main/resources/galaxy7-img.jpg", 620));
		phoneList.add(new Phone("Iphone", "Iphone X", "/src/main/resources/iphonex-img.jpg", 860));		
		CustomerOrder customerOrder = mapper.createCustomerOrder(customerOrderRequest, phoneList);

		assertNotNull(customerOrder);
		assertThat(customerOrder.getName(), is("Alberto"));
		assertThat(customerOrder.getSurname(), is("Marquina"));
		assertThat(customerOrder.getEmail(), is("albertoMarquina@gmail.com"));
		assertNotNull(customerOrder.getPhoneList());
		
		assertThat(customerOrder.getPhoneList().get(0).getName(), is("Samsung"));
		assertThat(customerOrder.getPhoneList().get(0).getDescription(), is("Samsung Galaxy 7"));
		assertThat(customerOrder.getPhoneList().get(0).getImage(), is("/src/main/resources/galaxy7-img.jpg"));
		assertThat(customerOrder.getPhoneList().get(0).getPrice(), is(620));
		assertThat(customerOrder.getPhoneList().get(1).getName(), is("Iphone"));
		assertThat(customerOrder.getPhoneList().get(1).getDescription(), is("Iphone X"));
		assertThat(customerOrder.getPhoneList().get(1).getImage(), is("/src/main/resources/iphonex-img.jpg"));
		assertThat(customerOrder.getPhoneList().get(1).getPrice(), is(860)); 
	}

}