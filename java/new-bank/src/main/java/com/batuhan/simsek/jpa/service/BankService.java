package com.batuhan.simsek.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhan.simsek.jpa.Mapper.AccountEntityToDto;
import com.batuhan.simsek.jpa.dto.AccountDto;
import com.batuhan.simsek.jpa.entity.Account;
import com.batuhan.simsek.jpa.entity.Customer;
import com.batuhan.simsek.jpa.repository.AccountRepository;
import com.batuhan.simsek.jpa.repository.CustomerRepository;

@Service
public class BankService {
	
	@Autowired
	AccountRepository accountRepository;
	CustomerRepository customerRepository;
	public List<AccountDto> getAccounts() {

		AccountEntityToDto converter=new AccountEntityToDto();
		return converter.convert(accountRepository.findAll());

	}
	
	public Optional<Account> getAccount(Integer identityNo) {
		return accountRepository.findByCustomerIdentityNo(identityNo);
	}
	
	public Optional<Customer> getCustomer(Integer identityNo) {
		return customerRepository.findByCustomerIdentityNo(identityNo);
	}
	
	public boolean createAccount(Integer identityNo, Integer iban) {

		Account account = new Account();
		
		account.setCustomerIdentityNo(identityNo);
		account.setBalance(10000);
		account.setIban(Integer.toString(iban));
		
		var ret=accountRepository.save(account);
		return true;
	}
}
