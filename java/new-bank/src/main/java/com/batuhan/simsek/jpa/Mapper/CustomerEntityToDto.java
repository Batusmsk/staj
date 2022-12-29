package com.batuhan.simsek.jpa.Mapper;

import java.util.ArrayList;
import java.util.List;

import com.batuhan.simsek.jpa.dto.CustomerDto;
import com.batuhan.simsek.jpa.entity.Customer;


public class CustomerEntityToDto {
	
	public CustomerDto convert(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerFullName(customer.getCustomerFullName());
		customerDto.setCustomerId(customer.getCustomerId());
		customerDto.setCustomerIdentityNo(customer.getCustomerIdentityNo());
		return customerDto;
	}

	public List<CustomerDto> convert(List<Customer> customers) {
		List<CustomerDto> returnList=new ArrayList<>();
		for(Customer customer:customers) {
			returnList.add(convert(customer));
		}
		return returnList;
	}
	

}
