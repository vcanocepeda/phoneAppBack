package com.example.phoneapp.model;

/**
 * 
 * Phone Dto
 * 
 * @author Vicente Cano Cepeda
 *
 */
public class PhoneDto {
	private long id;
	private String name;
	private String description;
	private String image;
	private int price;
	
	public PhoneDto() {}
	
	public PhoneDto(String name, String description, String image, int price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }
	
	public PhoneDto(long id, String name, String description, String image, int price) {
        this.id = id;
		this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }
	
	public long getId() {
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
