package com.batuhan.simsek.jpa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batuhan.simsek.jpa.entity.Account;
import com.batuhan.simsek.jpa.repository.Balance;
import com.batuhan.simsek.jpa.service.BankService;
import com.batuhan.simsek.jpa.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	@Autowired
	BankService bankService;
	
    @GetMapping(value = "/customer/{customerId}/")
    public ResponseEntity<Optional<Balance>> getBalance(@PathVariable("customerId") Integer customerId) {
    	return new ResponseEntity<Optional<Balance>>(customerService.getBalance(customerId), HttpStatus.OK);
    }
    
    @PutMapping(value = "/customer/{customerId}/{withdrawAmount}")
    public ResponseEntity<Boolean> withdraw(@PathVariable("customerId") Integer customerId,@PathVariable("withdrawAmount") Integer amount) {
    	return new ResponseEntity<Boolean>(customerService.withdraw(customerId, amount), HttpStatus.OK);
    }
    
    @PutMapping(value = "/customer/{customerId}/{depositAmount}")
    public ResponseEntity<Boolean> deposit(@PathVariable("customerId") Integer customerId, @PathVariable("depositAmount") Integer amount) {
    	
    	return new ResponseEntity<Boolean>(customerService.deposit(customerId, amount), HttpStatus.OK);
    }
	
}
