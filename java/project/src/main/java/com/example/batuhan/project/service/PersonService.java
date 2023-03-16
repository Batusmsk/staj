package com.example.batuhan.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.batuhan.project.dto.CreatePersonDto;
import com.example.batuhan.project.dto.PersonDto;
import com.example.batuhan.project.dto.PurchaseApartmentDto;
import com.example.batuhan.project.entity.Apartment;
import com.example.batuhan.project.entity.Person;
import com.example.batuhan.project.repository.PersonRepository;

@Service
public class PersonService {
	@Autowired
	PersonRepository personRepository;
	@Autowired
	BlockService blockService;
	@Autowired
	ApartmentService apartmentService;

	public String createPerson(CreatePersonDto dto) {
		Person person = new Person();
		person.setName(dto.getName());
		person.setLastName(dto.getLastName());
		person.setEmail(dto.getEmail());
		person.setPhoneNumber(dto.getPhoneNumber());
		personRepository.save(person);
		return "succesfully";
	}
	public Optional<Person> getPerson(String mail) {
		return personRepository.findById(mail);
	}
	public List<PersonDto> getPersons() {
		List<PersonDto> list = new ArrayList<>();
		
		for(var i:personRepository.findAll()) {
			PersonDto dto = new PersonDto();
			dto.setName(i.getName());
			dto.setLastName(i.getLastName());
			dto.setPhoneNumber(i.getPhoneNumber());
			dto.setEmail(i.getEmail());
			dto.setDebt(i.getDebt());
			list.add(dto);
		}
		return list;
	}
	public String addApartmentToPerson(PurchaseApartmentDto dto) {
		if(!apartmentService.getApartment(dto.getBlockNo(), dto.getApartmentNo()).isPresent()) return "apartment not found";
		if(getPerson(dto.getEmail()).isPresent() == false) return "Person not found";
		if(apartmentService.getApartment(dto.getBlockNo(), dto.getApartmentNo()).get().getPurchaseDate() != null && apartmentService.getApartment(dto.getBlockNo(), dto.getApartmentNo()).get().getPurchaseDate().length() > 3) return "The apartment has already been purchased.";
		Optional<Person> person = getPerson(dto.getEmail());
		List<Apartment> list = person.get().getPersonApartments();	
		Optional<Apartment> apartment = apartmentService.getApartment(dto.getBlockNo(), dto.getApartmentNo());
		apartment.get().setPurchaseDate(dto.getPurchaseDate());
		list.add(apartment.get());
		person.get().setPersonApartments(list);
		personRepository.save(person.get());
		apartmentService.saveOrUpdateApartment(apartment.get());
		return "successfully";
	}
	
    public List<Apartment> findApartmentsByPerson(String email) {
    	if(getPerson(email).isPresent() == false) return null;
    	List<Apartment> list = getPerson(email).get().getPersonApartments();
        return list;
    }
    
	public Optional<Person> findOwnerTheApartment(String blockName, Integer apartmentNo) {
		if(!apartmentService.getApartment(blockName, apartmentNo).isPresent()) return Optional.empty();
		Optional<Person> person = getPerson(apartmentService.getApartment(blockName, apartmentNo).get().getEmail());
		return person;
	}
	
	public String removeApartmentToPerson(String email, String blockName, Integer apartmentNo) {
		if(!apartmentService.getApartment(blockName, apartmentNo).isPresent()) return "apartment not found";
		if(getPerson(email).isPresent() == false) return "Person not found";
		if(getPerson(email).get().getDebt() > 0) return "The apartment cannot be vacated due to outstanding dues.";
		if(apartmentService.getApartment(blockName, apartmentNo).get().getPurchaseDate() == null || apartmentService.getApartment(blockName, apartmentNo).get().getPurchaseDate().length() < 4) return "this apartment has not been purchased yet.";
		Optional<Apartment> apartment = apartmentService.getApartment(blockName, apartmentNo);
		Optional<Person> person = getPerson(email);
		List<Apartment> list = person.get().getPersonApartments();
		for(var i:list) {
			if(i.getApartmentNo() == apartmentNo && i.getBlock().getBlockName().equals(blockName)) {
				list.remove(i);
			}
		}
		person.get().setPersonApartments(list);
		apartment.get().setPurchaseDate(null);
		personRepository.save(person.get());
		apartmentService.saveOrUpdateApartment(apartment.get());
		
		return "successfully";
	}
}
 