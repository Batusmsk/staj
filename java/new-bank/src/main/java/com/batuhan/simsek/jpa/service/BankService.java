package com.batuhan.simsek.jpa.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhan.simsek.jpa.Mapper.AccountEntityToDto;
import com.batuhan.simsek.jpa.dto.AccountDto;
import com.batuhan.simsek.jpa.entity.Account;
import com.batuhan.simsek.jpa.repository.AccountRepository;
import com.batuhan.simsek.jpa.repository.Balance;

@Service
public class BankService {
	
	@Autowired
	AccountRepository accountRepository;
	
	public List<AccountDto> getAccounts() {

		AccountEntityToDto converter=new AccountEntityToDto();
		return converter.convert(accountRepository.findAll());

	}
	
	public boolean createAccount(Integer identityNo) {
		Random random = new Random();
		int iban = random.nextInt(10000);
		
		Account account = new Account();
		
		account.setCustomerIdentityNo(identityNo);
		account.setBalance(10000);
		account.setIban(Integer.toString(iban));
		
		var ret=accountRepository.save(account);
		return true;
	}
}
