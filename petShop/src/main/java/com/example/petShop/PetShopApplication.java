package com.example.petShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.example.entities")
@SpringBootApplication(scanBasePackages= {"com.example.controllers", "com.example.service", "com.example.repository"})
@EnableJpaRepositories(basePackages = "com.example.repository")
public class PetShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetShopApplication.class, args);
	}
	
}
