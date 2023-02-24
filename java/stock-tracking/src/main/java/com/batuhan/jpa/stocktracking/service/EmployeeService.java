package com.batuhan.jpa.stocktracking.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhan.jpa.stocktracking.dto.CreateEmployeeDto;
import com.batuhan.jpa.stocktracking.dto.CreateSaleDto;
import com.batuhan.jpa.stocktracking.dto.SaleDto;
import com.batuhan.jpa.stocktracking.entity.Employees;
import com.batuhan.jpa.stocktracking.entity.Sales;
import com.batuhan.jpa.stocktracking.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	ProductService productService;
	public String createEmployee(CreateEmployeeDto createEmployeeDto) {
		Employees employee = new Employees();
		employee.setFullName(createEmployeeDto.getFullName());
		employee.setWage(createEmployeeDto.getWage());
		employeeRepository.save(employee);
		return employee.toString();
	}
	
	public Optional<Employees> getEmployee(Integer id) {
		return employeeRepository.findById(id);
	}
	public List<Employees> getEmployees() {
		return employeeRepository.findAll();
	}
	public boolean createSale(CreateSaleDto createSaleDto) {
		Optional<Employees> employee = employeeRepository.findById(createSaleDto.getEmployeeId());
		List<Sales> sale = new ArrayList<Sales>();
        SimpleDateFormat d = new SimpleDateFormat();
        Date date = new Date();
        
		for(SaleDto createSaleDto1:createSaleDto.getSales()) {
			Sales sales = new Sales();
			if(productService.getProduct(createSaleDto1.getProductId()).get().getProductCountStocks() < 1) return false;
			//sales.setAmount(createSaleDto1.getAmount());
			sales.setSaleDate(d.format(date));
			sales.setProductId(createSaleDto1.getProductId());
			sales.setProductPrice(productService.getProduct(createSaleDto1.getProductId()).map(p -> p.getProductPrice()).get());
			sales.setEmployees(employee.get());
			sale.add(sales);
			var x = productService.productStockUpdate(createSaleDto1.getProductId(), -1);
		}
		employee.get().setSales(sale);
		employeeRepository.save(employee.get());
		return true;
		
	}
}
