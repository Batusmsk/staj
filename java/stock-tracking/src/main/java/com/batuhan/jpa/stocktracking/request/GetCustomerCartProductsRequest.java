package com.batuhan.jpa.stocktracking.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCustomerCartProductsRequest {
	private String date;
	private String hour;
	private Integer id;
}
