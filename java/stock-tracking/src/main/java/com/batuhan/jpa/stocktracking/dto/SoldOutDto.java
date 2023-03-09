package com.batuhan.jpa.stocktracking.dto;

import lombok.Data;

@Data
public class SoldOutDto {
	private Integer employeeId;
	private Integer saleId;
	private String saleDate;
	private Integer productPrice;
	private Integer productId;
	private String categoryName;
}
