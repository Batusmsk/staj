package com.batuhan.jpa.stocktracking.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSaleDto {
	public Integer employeeId;
	public List<SaleDto> sales;
}
