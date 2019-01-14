package com.example.phoneapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.phoneapp.entity.AppUser;
import com.example.phoneapp.entity.Phone;
import com.example.phoneapp.repository.AppUserRepository;
import com.example.phoneapp.repository.CustomerOrderRepository;
import com.example.phoneapp.repository.PhoneRepository;
import com.example.phoneapp.service.PhoneServiceImpl;

/**
 * 
 * Phone App Spring Boot data initialization
 * 
 * @author Vicente Cano Cepeda
 *
 */
@SpringBootApplication
public class PhoneAppSpringBoot {

	public static void main(String[] args) {
		SpringApplication.run(PhoneAppSpringBoot.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(PhoneServiceImpl.class);

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner demo(PhoneRepository phoneRepository, CustomerOrderRepository customerRepository,
			AppUserRepository appUserRepository) {
		return (args) -> {
			phoneRepository
					.save(new Phone(1, "Samsung", "Samsung Galaxy S7", "/src/main/resources/galaxy_s7-img.jpg", 650));
			phoneRepository.save(new Phone(2, "Huawei", "Huawei P20", "/src/main/resources/huawei_p20-img.jpg", 430));
			phoneRepository.save(new Phone(3, "BQ", "Aquaris X2", "/src/main/resources/bq_aquaris_x2-img.jpg", 380));
			phoneRepository.save(new Phone(4, "Iphone", "Iphone X", "/src/main/resources/iphone_x-img.jpg", 730));
			phoneRepository.save(new Phone(5, "Xiaomi", "Xiaomi Me4", "/src/main/resources/xiaomi_me4-img.jpg", 315));

			appUserRepository
					.save(new AppUser(1, "admin", "$2a$10$4v3qc/AsCdF1FAAXhfKg8emUXI5c5ZUNbjBaUkPH9u2u5TpikeqYS"));

			log.info("-------------------------------");
			for (Phone phone : phoneRepository.findAll()) {
				log.info("Phones:" + phone.toString());
			}
		};

	}
}
