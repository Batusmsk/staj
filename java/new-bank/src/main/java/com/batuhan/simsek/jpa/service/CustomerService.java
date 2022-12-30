package com.batuhan.simsek.jpa.service;

import java.io.Console;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.ConsoleHandler;

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
import com.batuhan.simsek.jpa.requests.TransferRequest;



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
		Optional<Account> account = accountRepository.findByCustomerIdentityNo(identityNo);
		if(!account.isPresent()) return Optional.empty();
		
		return accountRepository.findBalance(identityNo);
	}
	

	public String transfer(TransferRequest input) {
		Optional<Account> userToSend = accountRepository.findByIban(input.targetIban);
		Optional<Account> senderAccount = accountRepository.findByIban(input.senderIban);
		if(!senderAccount.isPresent()) return "wrong iban";
		if(!userToSend.isPresent()) return "wrong iban";
		if(input.senderIban.equals(input.targetIban)) return "you cant send it to yourself";
		if(customerRepository.findByCustomerIdentityNo(userToSend.get().getCustomerIdentityNo()).get().getCustomerFullName().toLowerCase().equals(input.targetFullName.toLowerCase())) {
			if(input.amount > accountRepository.findByIban(input.senderIban).get().getBalance()) return "insufficient balance";
			if(input.amount < 0) return "wrong balance";
			
			userToSend.get().setBalance(userToSend.get().getBalance() + input.amount);
			accountRepository.save(userToSend.get());
			
			senderAccount.get().setBalance(senderAccount.get().getBalance() - input.amount);
			accountRepository.save(senderAccount.get());
			return "Sender: " +senderAccount.toString()+  System.lineSeparator() +"Target: " + userToSend.toString();
		} else {
			return "wrong name";
		}
		
	}
	
	public boolean withdraw(Integer identityNo, Integer amount) {
		Integer costumerBalance = getBalance(identityNo).get().getBalance(); 
		if(amount > costumerBalance) return false;
		if(amount < 1) return false;
		
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
		if(amount < 0) return false;
		Optional<Account> account = accountRepository.findByCustomerIdentityNo(identityNo);
		if(account.isPresent()) {
		account.get().setBalance(costumerBalance + amount);
		accountRepository.save(account.get());
		return true;
		}
		return false;
	}
	
	public boolean createCustomer(CustomerDto customerDto) {
		Customer customer = new Customer();
		Random random = new Random();
		int iban = random.nextInt(100);
		while(accountRepository.findByIban(Integer.toString(iban)).isPresent()) { 
			iban = random.nextInt(100);
		}
		
		if(accountRepository.findByCustomerIdentityNo(customerDto.getCustomerIdentityNo()).isPresent()) return false;
		customer.setCustomerFullName(customerDto.getCustomerFullName());
		customer.setCustomerIdentityNo(customerDto.getCustomerIdentityNo());
		var ret=customerRepository.save(customer);
		
		bankService.createAccount(customerDto.getCustomerIdentityNo(), iban);
		
		return true;
	}



}
