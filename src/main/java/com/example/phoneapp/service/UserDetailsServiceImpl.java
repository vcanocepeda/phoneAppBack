package com.example.phoneapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.phoneapp.entity.AppUser;
import com.example.phoneapp.repository.AppUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		AppUser appUser = appUserRepository.findByUsername(username);
		if (appUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(appUser.getUsername(), appUser.getPassword(), new ArrayList<>());
	}

}
