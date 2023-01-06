package com.batuhan.jpa.stocktracking.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class SaleDto {
	
	@JsonIgnore
	private Integer saleId;
	@JsonIgnore
	private String date;
	@JsonIgnore
	private Integer productPrice;
	private Integer productId;

}
