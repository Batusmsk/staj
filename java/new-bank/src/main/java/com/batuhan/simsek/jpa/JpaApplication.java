package com.batuhan.simsek.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.batuhan.simsek.jpa.dto.CustomerDto;
import com.batuhan.simsek.jpa.service.CustomerService;

@SpringBootApplication
@EntityScan("com.batuhan.simsek")
@ComponentScan({ "com.batuhan.simsek" })
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
		
	}

}
