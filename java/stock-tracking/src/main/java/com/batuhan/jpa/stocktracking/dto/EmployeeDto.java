package com.batuhan.jpa.stocktracking.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    Integer employeeId;
	String fullName;
	Integer wage;
    private List<SaleDto> sales;
}
