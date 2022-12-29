package com.batuhan.simsek.jpa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
	
    private Integer accountId;
    private Integer customerIdentityNo;
    private Integer balance;
    private String iban;
    
}
