package com.batuhan.jpa.stocktracking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.batuhan.jpa.stocktracking.dto.CustomerDto;
import com.batuhan.jpa.stocktracking.repository.CustomerRepository;

public class x {
	@Autowired
	CustomerRepository customerRepository;
	
	public List<CustomerDto> convert() {
		List<CustomerDto> customerList = new ArrayList<>();
		for(var customer : customerRepository.findAll()) {
			CustomerDto customerDto = new CustomerDto();
			customerDto.setCreateDate(customer.getCreateDate());
			customerDto.setFullName(customer.getFullName());
			customerDto.setShoppingCart(customer.getShoppingCart());
			customerList.add(customerDto);
		}
		return customerList;
	}
}
