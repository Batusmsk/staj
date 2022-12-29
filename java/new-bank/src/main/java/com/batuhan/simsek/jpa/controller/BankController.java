package com.batuhan.simsek.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.batuhan.simsek.jpa.dto.AccountDto;
import com.batuhan.simsek.jpa.dto.CustomerDto;
import com.batuhan.simsek.jpa.service.BankService;
import com.batuhan.simsek.jpa.service.CustomerService;

@RestController
public class BankController {

	@Autowired
	CustomerService customerService;
	@Autowired
	BankService bankService;
	
   
    @PostMapping(value = "/customer")
	public boolean createCustomer(@RequestBody CustomerDto customerDto){
		return customerService.createCustomer(customerDto);
	}
    
    @GetMapping(value = "/customer")
	public ResponseEntity<List<CustomerDto>> getCustomers(){
		return new ResponseEntity<List<CustomerDto>>(customerService.getCustomers(),HttpStatus.OK);
	}
    
    @GetMapping(value = "/account")
	public ResponseEntity<List<AccountDto>> getAccounts(){
		return new ResponseEntity<List<AccountDto>>(bankService.getAccounts(),HttpStatus.OK);
	}
    
    
	
}
