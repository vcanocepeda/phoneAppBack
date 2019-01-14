package com.example.phoneapp.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * 
 * Customer Order entity
 * 
 * @author Vicente Cano Cepeda
 *
 */
@Entity
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long orderId;
	private String name;
	private String surname;   
	private String email;
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE})
	private List<Phone> phoneList;
	
	public CustomerOrder() {}
	
	public CustomerOrder(String name, String surname, String email) {
		this.name = name;
	    this.surname = surname;
	    this.email = email;
	}
	
	public CustomerOrder(String name, String surname, String email, List<Phone> phoneList) {
		this.name = name;
	    this.surname = surname;
	    this.email = email;
	    this.setPhoneList(phoneList);
	}
	
	@Override
    public String toString() {
        return String.format(
                "CustomerOrder[id=%d, name='%s', surname='%s', email='%s']",
                orderId, name, surname, email);
    }
	
	public Long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

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
	public List<Phone> getPhoneList() {
		return phoneList;
	}	
	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}
}