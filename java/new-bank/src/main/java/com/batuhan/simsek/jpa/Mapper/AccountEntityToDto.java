package com.batuhan.simsek.jpa.Mapper;

import java.util.ArrayList;
import java.util.List;

import com.batuhan.simsek.jpa.dto.AccountDto;
import com.batuhan.simsek.jpa.entity.Account;

public class AccountEntityToDto {
	
	public AccountDto convert(Account account) {
		AccountDto accountDto = new AccountDto();
		
		accountDto.setAccountId(account.getAccountId());
		accountDto.setCustomerIdentityNo(account.getCustomerIdentityNo());
		accountDto.setIban(account.getIban());
		accountDto.setBalance(account.getBalance());

		return accountDto;
	}

	public List<AccountDto> convert(List<Account> accounts) {
		List<AccountDto> returnList=new ArrayList<>();
		for(Account account:accounts) {
			returnList.add(convert(account));
		}
		return returnList;
	}
}
