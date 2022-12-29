package com.batuhan.simsek.jpa.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {
	public Integer customerIdentityNo;
	public Integer idOfSentCostumer;
	public Integer amount;
}
