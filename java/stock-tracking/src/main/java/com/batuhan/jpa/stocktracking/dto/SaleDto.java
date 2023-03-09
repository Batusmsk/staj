package com.batuhan.jpa.stocktracking.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class SaleDto {
	
	@JsonIgnore
	private Integer saleId;
	@JsonIgnore
	private String saleDate;
	@JsonIgnore
	private Integer productPrice;
	private Integer productId;
	private String categoryName;
}
