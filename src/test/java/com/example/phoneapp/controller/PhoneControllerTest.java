package com.example.phoneapp.controller;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.isA;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.phoneapp.PhoneAppSpringBoot;
import com.example.phoneapp.model.CustomerOrderRequest;
import com.example.phoneapp.model.CustomerOrderResponse;
import com.example.phoneapp.model.PhoneDto;
import com.example.phoneapp.model.PhoneListResponse;
import com.example.phoneapp.model.PhoneRequest;
import com.example.phoneapp.model.PhoneResponse;
import com.example.phoneapp.service.PhoneService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PhoneAppSpringBoot.class)
@TestPropertySource("classpath:application.properties")
public class PhoneControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private PhoneService phoneService;
	
	@InjectMocks
	private PhoneController phoneController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(phoneController).build();
	}
	
	@Test
	public void getPhoneEmbeddedDB_OK() throws Exception {
				
		when(phoneService.getPhone("1")).thenReturn(new PhoneDto(6, "LG", "LG G7", "/src/main/resources/lg_g7-img.jpg", 580));
		mockMvc.perform(MockMvcRequestBuilders.get("/phone/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void getPhoneEmbeddedDB_NoContent() throws Exception {
				
		when(phoneService.getPhone("1")).thenReturn(new PhoneDto());
		mockMvc.perform(MockMvcRequestBuilders.get("/phone/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void getListPhonesFromFile_OK() throws Exception {
				
		when(phoneService.listPhonesFromFile()).thenReturn(createPhoneResponseMock());
		mockMvc.perform(MockMvcRequestBuilders.get("/phone").param("fromFile", "true").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void getListPhonesFromFile_NoContent() throws Exception {
				
		when(phoneService.listPhonesFromFile()).thenReturn(new PhoneListResponse());
		mockMvc.perform(MockMvcRequestBuilders.get("/phone").param("fromFile", "true").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void getListPhonesEmbeddedDB_OK() throws Exception {
				
		when(phoneService.listPhonesFromEmbeddedDB()).thenReturn(createPhoneResponseMock());
		mockMvc.perform(MockMvcRequestBuilders.get("/phone").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void getListPhonesEmbeddedDB_NoContent() throws Exception {
				
		when(phoneService.listPhonesFromEmbeddedDB()).thenReturn(new PhoneListResponse());
		mockMvc.perform(MockMvcRequestBuilders.get("/phone").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void savePhone_OK() throws Exception {	
		PhoneResponse phoneResponse = new PhoneResponse();
		phoneResponse.setPhone(new PhoneDto(6, "LG", "LG G7", "/src/main/resources/lg_g7-img.jpg", 580));
		
		when(phoneService.savePhone(isA(PhoneRequest.class))).thenReturn(phoneResponse);
		mockMvc.perform(MockMvcRequestBuilders.post("/phone").contentType(MediaType.APPLICATION_JSON)
		.content("{\"phone\": { \"id\": 6, \"name\": \"LG\", \"description\": \"LG G7\"," + 
		"\"image\": \"/src/main/resources/lg_g7-img.jpg\", \"price\": 580 } }")).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.phone.id").value(phoneResponse.getPhone().getId()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.phone.name").value(phoneResponse.getPhone().getName()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.phone.description").value(phoneResponse.getPhone().getDescription()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.phone.image").value(phoneResponse.getPhone().getImage()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.phone.price").value(phoneResponse.getPhone().getPrice()));
	}
	
	@Test
	public void saveCustomerOrder_OK() throws Exception {	
		CustomerOrderResponse customerOrderResponse = new CustomerOrderResponse();
		customerOrderResponse.setOrderPrice(650);
		
		when(phoneService.saveCustomerOrder(isA(CustomerOrderRequest.class))).thenReturn(customerOrderResponse);
		mockMvc.perform(MockMvcRequestBuilders.post("/customerOrder").contentType(MediaType.APPLICATION_JSON)
		.content("{\"name\": \"Angel\", \"surname\": \"Marquina\", \"email\": \"angelMarquina@gmail.com\", \"phoneList\": [" + 
		"{ \"id\": 1, \"name\": \"Samsung\", \"description\": \"Samsung Galaxy S7\"," + 
		"\"image\": \"/src/main/resources/galaxy_s7-img.jpg\", \"price\": 650 } ] }")).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.orderPrice").value(650));
	}
	
	private PhoneListResponse createPhoneResponseMock() {		
		List<PhoneDto> phoneList = new ArrayList<PhoneDto>();		
		phoneList.add(new PhoneDto("Samsung", "Samsung Galaxy 7", "/src/main/resources/galaxy7-img.jpg", 620));
		phoneList.add(new PhoneDto("Iphone", "Iphone X", "/src/main/resources/iphonex-img.jpg", 860));
		PhoneListResponse response = new PhoneListResponse();
		response.setPhoneList(phoneList);
		return response;
	}
}
