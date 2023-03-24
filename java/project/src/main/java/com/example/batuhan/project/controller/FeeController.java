package com.example.batuhan.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.batuhan.project.dto.FeeDto;
import com.example.batuhan.project.request_response.FeeRequest;
import com.example.batuhan.project.service.FeeService;

@RestController
public class FeeController {
	@Autowired
	FeeService feeService;
	
	@PostMapping(value = "/fee/createFee")
	public String createFee(@RequestBody FeeRequest request) {
		return feeService.createFee(request);
	}
	@GetMapping(value = "/fee/findByPerson")
	public List<FeeDto> findByPerson(@RequestParam String email) {
		return feeService.findByPerson(email);
	}
	@GetMapping(value = "/fee/findByBlockNameAndApartmentNo")
	public List<FeeDto> findByBlockNameAndApartmentNo(@RequestParam String blockName, @RequestParam Integer apartmentNo) {
		return feeService.findByBlockNameAndApartmentNo(blockName, apartmentNo);
	}
	@PutMapping(value = "/fee/payFee/{id}")
	public String payFee(@PathVariable("id") Integer id) {
		return feeService.payFee(id, 0);
	}
	@PutMapping(value = "/fee/payWithAmount")
	public String amountWithPayment(@RequestParam Integer id, @RequestParam Integer amount) {
		return feeService.amountWithPayment(id, amount);
	}
}
