package com.example.phoneapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.phoneapp.entity.AppUser;

/**
 * 
 * AppUser Repository
 * 
 * @author Vicente Cano Cepeda
 *
 */
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
