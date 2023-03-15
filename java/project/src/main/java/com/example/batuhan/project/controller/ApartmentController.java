package com.example.batuhan.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.batuhan.project.dto.ApartmentDto;
import com.example.batuhan.project.service.ApartmentService;

@RestController
public class ApartmentController {
	@Autowired
	ApartmentService apartmentService;
	@PostMapping(value = "/apartment/createApartment")
	public String createApartment(@RequestBody ApartmentDto apartment) {
		return apartmentService.createApartment(apartment);
	}
	//aptOnTheFloor
	@GetMapping(value = "/apartment/aptOnTheFloor/{block}/{floor}")
	public ResponseEntity<List<ApartmentDto>> getAptOnTheFloor(@PathVariable("block") String block, @PathVariable("floor") Integer floor) {
		return new ResponseEntity<List<ApartmentDto>>(apartmentService.aptOnTheFloor(block, floor), HttpStatus.OK);
	}
}
