package com.example.phoneapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * 
 * Phone Entity
 * 
 * @author Vicente Cano Cepeda
 *
 */
@Entity
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private String image;
	private int price;
	@ManyToMany(mappedBy = "phoneList")
	private List<CustomerOrder> customerOrders = new ArrayList<>();

	public Phone() {
	}

	public Phone(String name, String description, int price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Phone(String name, String description, String image, int price) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
	}

	public Phone(long id, String name, String description, String image, int price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("Phone[id=%d, name='%s', description='%s', image='%s' , price='%d']", id, name,
				description, image, price);
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}