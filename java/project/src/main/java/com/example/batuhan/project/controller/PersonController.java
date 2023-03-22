package com.example.batuhan.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.batuhan.project.dto.PurchaseApartmentDto;
import com.example.batuhan.project.entity.Apartment;
import com.example.batuhan.project.entity.Person;
import com.example.batuhan.project.request.RegisterRequest;
import com.example.batuhan.project.service.PersonService;

@RestController
public class PersonController {
	@Autowired
	PersonService personService;
	
	@PostMapping(value = "/person/createPerson")
	public String createPerson(@RequestBody RegisterRequest registerRequest) {
		return personService.createPerson(registerRequest);
	}
	//@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/person/getPerson/{mail}")
	public Optional<Person> getPerson(@PathVariable("mail") String mail) {
		return personService.getPerson(mail);
	}
	@PostMapping(value = "/person/addApartmentToPerson")
	public String addApartmentToPerson(@RequestBody PurchaseApartmentDto purchaseApartmentDto) {
		return personService.addApartmentToPerson(purchaseApartmentDto);
	}
	@PutMapping(value = "/person/removeApartmentToPerson/{email}/{blockName}/{apartmentNo}")
	public String removeApartmentToPerson(@PathVariable("email") String email, @PathVariable("blockName") String blockName,@PathVariable("apartmentNo") Integer apartmentNo) {
		return personService.removeApartmentToPerson(email, blockName, apartmentNo);
	}
	@GetMapping(value = "/person/findOwnerTheApartment/{blockName}/{apartmentNo}")
	public Optional<Person> findOwnerTheApartment(@PathVariable("blockName") String blockName, @PathVariable("apartmentNo") Integer apartmentNo) {
		return personService.findOwnerTheApartment(blockName, apartmentNo);
	}
	
	@GetMapping(value = "/person/findApartmentsByPerson/{email}")
	public List<Apartment> findApartmentsByPerson(@PathVariable("email") String email) {
		return personService.findApartmentsByPerson(email);	
	}
}
 