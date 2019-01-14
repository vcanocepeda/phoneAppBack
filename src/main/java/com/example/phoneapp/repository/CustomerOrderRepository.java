package com.example.phoneapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.phoneapp.entity.CustomerOrder;

/**
 * 
 * Customer Order Repository
 * 
 * @author Vicente Cano Cepeda
 *
 */
public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Long> {
	
}