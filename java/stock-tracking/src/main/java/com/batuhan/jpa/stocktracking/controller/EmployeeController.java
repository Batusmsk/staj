package com.batuhan.jpa.stocktracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.batuhan.jpa.stocktracking.dto.CreateEmployeeDto;
import com.batuhan.jpa.stocktracking.dto.CreateSaleDto;
import com.batuhan.jpa.stocktracking.service.EmployeeService;
import com.batuhan.jpa.stocktracking.service.ProductService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	ProductService productService;
	
    @PostMapping(value = "/employee")
	public String createEmployee(@RequestBody CreateEmployeeDto creeateEmployeeDto){
		return employeeService.createEmployee(creeateEmployeeDto);
	}
    
    @PostMapping(value = "/sale/create")
	public boolean createSale(@Validated @RequestBody CreateSaleDto creeateSaleDto){
		return employeeService.createSale(creeateSaleDto);
	}
}
