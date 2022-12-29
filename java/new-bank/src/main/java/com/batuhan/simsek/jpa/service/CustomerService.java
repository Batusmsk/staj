package com.batuhan.simsek.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhan.simsek.jpa.Mapper.AccountEntityToDto;
import com.batuhan.simsek.jpa.Mapper.CustomerEntityToDto;
import com.batuhan.simsek.jpa.dto.CustomerDto;
import com.batuhan.simsek.jpa.entity.Account;
import com.batuhan.simsek.jpa.entity.Customer;
import com.batuhan.simsek.jpa.repository.AccountRepository;
import com.batuhan.simsek.jpa.repository.Balance;
import com.batuhan.simsek.jpa.repository.CustomerRepository;



@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	BankService bankService;
	
	public List<CustomerDto> getCustomers() {
		CustomerEntityToDto converter = new CustomerEntityToDto();
		return converter.convert(customerRepository.findAll());
	}
	
	public Optional<Balance> getBalance(Integer identityNo) {
		AccountEntityToDto converter=new AccountEntityToDto();
		
		return accountRepository.findBalance(identityNo);
	}
	
	public boolean withdraw(Integer identityNo, Integer amount) {
		Integer costumerBalance = getBalance(identityNo).get().getBalance(); 
		if(amount > costumerBalance) return false;
		if(amount < 0) return false;
		
		Optional<Account> account = accountRepository.findByCustomerIdentityNo(identityNo);
//		return account.map(a ->{
//			a.setBalance(costumerBalance - amount);
//			accountRepository.save(a);
//			return Boolean .TRUE;
//		}).orElse(Boolean.FALSE);
		
		if(!account.isPresent()) return false;
		account.get().setBalance(costumerBalance - amount);
		accountRepository.save(account.get());
		return true;
	}
	
	public boolean deposit(Integer identityNo, Integer amount) {
		Integer costumerBalance = getBalance(identityNo).get().getBalance(); 
		if(amount > 0) return false;
		Optional<Account> account = accountRepository.findByCustomerIdentityNo(identityNo);
		account.get().setBalance(costumerBalance + amount);
		accountRepository.save(account.get());
		return true;
	}
	
	public boolean createCustomer(CustomerDto customerDto) {
		Customer customer = new Customer();
		
		customer.setCustomerFullName(customerDto.getCustomerFullName());
		customer.setCustomerIdentityNo(customerDto.getCustomerIdentityNo());
		var ret=customerRepository.save(customer);
		
		bankService.createAccount(customerDto.getCustomerIdentityNo());
		
		return true;
	}



}
