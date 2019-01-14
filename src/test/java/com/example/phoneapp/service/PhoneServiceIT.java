package com.example.phoneapp.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.example.phoneapp.PhoneAppSpringBoot;
import com.example.phoneapp.entity.Phone;
import com.example.phoneapp.exception.PhoneException;
import com.example.phoneapp.model.CustomerOrderRequest;
import com.example.phoneapp.model.CustomerOrderResponse;
import com.example.phoneapp.model.PhoneDto;
import com.example.phoneapp.model.PhoneListResponse;
import com.example.phoneapp.model.PhoneRequest;
import com.example.phoneapp.model.PhoneResponse;
import com.example.phoneapp.repository.PhoneRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PhoneAppSpringBoot.class)
@TestPropertySource("classpath:application.properties")
public class PhoneServiceIT {
	
	public static boolean dbInit = false;
	
	@Autowired
	public PhoneService phoneService;
	
	@Autowired
	public PhoneRepository phoneRepository;
	
	@Before
	public void setUp() throws Exception {
		if(!dbInit){
			phoneRepository.save(new Phone(1, "Samsung", "Samsung Galaxy S7", "/src/main/resources/galaxy_s7-img.jpg", 650));
			phoneRepository.save(new Phone(2, "Huawei", "Huawei P20", "/src/main/resources/huawei_p20-img.jpg", 430));
			phoneRepository.save(new Phone(3, "BQ", "Aquaris X2", "/src/main/resources/bq_aquaris_x2-img.jpg", 380));
			phoneRepository.save(new Phone(4, "Iphone", "Iphone X", "/src/main/resources/iphone_x-img.jpg", 730));
			phoneRepository.save(new Phone(5, "Xiaomi", "Xiaomi Me4", "/src/main/resources/xiaomi_me4-img.jpg", 315));
			dbInit = true;
		}
	}
	
	@Test
	public void getPhoneTestOK() {
		PhoneDto phoneDto = phoneService.getPhone("1");
		assertNotNull(phoneDto);
		assertThat(phoneDto.getName(), is("Samsung"));
		assertThat(phoneDto.getDescription(), is("Samsung Galaxy S7"));
		assertThat(phoneDto.getImage(), is("/src/main/resources/galaxy_s7-img.jpg"));
		assertThat(phoneDto.getPrice(), is(650));
	}
	
	@Test
	public void listPhonesFromFileTestOK() {
		PhoneListResponse phoneResponse = phoneService.listPhonesFromFile();
		
		assertNotNull(phoneResponse.getPhoneList());
		assertThat(phoneResponse.getPhoneList().isEmpty(), is(false));
		assertThat(phoneResponse.getPhoneList().get(0).getId(), is(1L));
		assertThat(phoneResponse.getPhoneList().get(0).getName(), is("Samsung"));
		assertThat(phoneResponse.getPhoneList().get(0).getDescription(), is("Samsung Galaxy S7"));
		assertThat(phoneResponse.getPhoneList().get(0).getImage(), is("/src/main/resources/galaxy_s7-img.jpg"));
		assertThat(phoneResponse.getPhoneList().get(0).getPrice(), is(650));
		assertThat(phoneResponse.getPhoneList().get(1).getId(), is(2L));
		assertThat(phoneResponse.getPhoneList().get(1).getName(), is("Huawei"));
		assertThat(phoneResponse.getPhoneList().get(1).getDescription(), is("Huawei P20"));
		assertThat(phoneResponse.getPhoneList().get(1).getImage(), is("/src/main/resources/huawei_p20-img.jpg"));
		assertThat(phoneResponse.getPhoneList().get(1).getPrice(), is(430));	
	}
	
	@Test
	public void listPhonesFromEmbeddedDBTestOK() {		
		PhoneListResponse phoneResponse = phoneService.listPhonesFromEmbeddedDB();
		
		assertNotNull(phoneResponse.getPhoneList());
		assertThat(phoneResponse.getPhoneList().isEmpty(), is(false));
		assertThat(phoneResponse.getPhoneList().get(0).getId(), is(1L));
		assertThat(phoneResponse.getPhoneList().get(0).getName(), is("Samsung"));
		assertThat(phoneResponse.getPhoneList().get(0).getDescription(), is("Samsung Galaxy S7"));
		assertThat(phoneResponse.getPhoneList().get(0).getImage(), is("/src/main/resources/galaxy_s7-img.jpg"));
		assertThat(phoneResponse.getPhoneList().get(0).getPrice(), is(650));
		assertThat(phoneResponse.getPhoneList().get(1).getId(), is(2L));
		assertThat(phoneResponse.getPhoneList().get(1).getName(), is("Huawei"));
		assertThat(phoneResponse.getPhoneList().get(1).getDescription(), is("Huawei P20"));
		assertThat(phoneResponse.getPhoneList().get(1).getImage(), is("/src/main/resources/huawei_p20-img.jpg"));
		assertThat(phoneResponse.getPhoneList().get(1).getPrice(), is(430));
	}
	
	@Test
	public void savePhoneTest_OK() {		
		PhoneRequest phoneRequest = new PhoneRequest();
		phoneRequest.setPhone(new PhoneDto(6, "LG", "LG G7", "/src/main/resources/lg_g7-img.jpg", 580));
		
		PhoneResponse phoneResponse = phoneService.savePhone(phoneRequest);
		
		assertNotNull(phoneResponse);
		assertNotNull(phoneResponse.getPhone());
		assertThat(phoneResponse.getPhone().getName(), is("LG"));
		assertThat(phoneResponse.getPhone().getDescription(), is("LG G7"));
		assertThat(phoneResponse.getPhone().getImage(), is("/src/main/resources/lg_g7-img.jpg"));
		assertThat(phoneResponse.getPhone().getPrice(), is(580));
	}
	
	@Test
	public void saveCustomerOrderTest_OK() {	
		CustomerOrderRequest customerOrderRequest = new CustomerOrderRequest();	
		customerOrderRequest.setSurname("Alberto");
		customerOrderRequest.setSurname("Marquina");
		customerOrderRequest.setEmail("albertoMarquina@gmail.com");

		List<PhoneDto> phoneList = new ArrayList<PhoneDto>();
		phoneList.add(new PhoneDto(1L, "BQ", "BQ Aquaris X2", "/src/main/resources/iphonex-img.jpg", 380));
		phoneList.add(new PhoneDto(2L, "Samsung", "Samsung Galaxy 7", "/src/main/resources/galaxy7-img.jpg", 620));
		customerOrderRequest.setPhoneList(phoneList);
		
		CustomerOrderResponse customerOrderResponse = phoneService.saveCustomerOrder(customerOrderRequest);
		assertNotNull(customerOrderResponse);
		assertThat(customerOrderResponse.getOrderPrice(), is(1000));
	}
	

	@Test(expected = PhoneException.class)
	public void saveCustomerOrder_ThrowsPhoneException() throws Exception {

		CustomerOrderRequest customerOrderRequest = new CustomerOrderRequest();	
		customerOrderRequest.setSurname("Alberto");
		customerOrderRequest.setSurname("Marquina");
		customerOrderRequest.setEmail("albertoMarquina@gmail.com");

		List<PhoneDto> phoneList = new ArrayList<PhoneDto>();
		phoneList.add(new PhoneDto(1L, "BQ", "BQ Aquaris X2", "/src/main/resources/iphonex-img.jpg", 380));
		phoneList.add(new PhoneDto(6L, "Samsung", "Samsung Galaxy 7", "/src/main/resources/galaxy7-img.jpg", 620));
		customerOrderRequest.setPhoneList(phoneList);
		
		CustomerOrderResponse customerOrderResponse = phoneService.saveCustomerOrder(customerOrderRequest);
	}
}
