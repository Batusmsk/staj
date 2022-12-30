package com.batuhan.simsek.jpa.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {
	
	public String senderIban;
	
	public String targetIban;
	public String targetFullName;
	public Integer amount;
}
