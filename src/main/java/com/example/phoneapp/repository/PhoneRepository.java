package com.example.phoneapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.phoneapp.entity.Phone;

/**
 * 
 * Phone Repository
 * 
 * @author Vicente Cano Cepeda
 *
 */
public interface PhoneRepository extends CrudRepository<Phone, Long> {
	
}