package com.batuhan.jpa.stocktracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.batuhan.jpa.stocktracking.service.ProductService;

@SpringBootApplication
public class StockTrackingApplication {
	@Autowired
	static
	 ProductService productService;
	public static void main(String[] args) {

		SpringApplication.run(StockTrackingApplication.class, args);
		
	}

}
