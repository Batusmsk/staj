package com.batuhan.jpa.stocktracking.dto;

import lombok.Data;

@Data
public class SoldOutDto {
	private Integer saleId;
	private String date;
	private Integer productPrice;
	private Integer productId;
}
