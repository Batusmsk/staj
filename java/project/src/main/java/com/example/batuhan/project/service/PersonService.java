package com.example.batuhan.project.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.batuhan.project.auth.UserDetailsService;
import com.example.batuhan.project.dto.PersonDto;
import com.example.batuhan.project.dto.PurchaseApartmentDto;
import com.example.batuhan.project.entity.Apartment;
import com.example.batuhan.project.entity.Person;
import com.example.batuhan.project.entity.PersonRole;
import com.example.batuhan.project.repository.PersonRepository;
import com.example.batuhan.project.request_response.FindOwnerResponse;
import com.example.batuhan.project.request_response.RegisterRequest;

@Service
public class PersonService {
	@Autowired
	PersonRepository personRepository;
	@Autowired
	BlockService blockService;
	@Autowired
	ApartmentService apartmentService;
	@Autowired
	FeeService feeService;
	@Autowired
	UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
	public String createPerson(RegisterRequest request) {
		
		Set<PersonRole> roles = new HashSet<>();
		roles.add(PersonRole.USER);
		
		Person person = new Person();
		person.setName(request.getName());
		person.setLastName(request.getLastName());
		person.setEmail(request.getEmail());
		person.setPassword(passwordEncoder.encode(request.getPassword()));
		person.setRoles(roles);
		person.setPhoneNumber(request.getPhoneNumber());
		personRepository.save(person);
		return "Kullanıcı oluşturuldu";
	}
	public String addRole(String mail, String role) {
		Person person = getPerson(mail).get();
		Set<PersonRole> roles = person.getRoles();
		
		if(role.toLowerCase().equals("user")) {
			roles.add(PersonRole.USER);
		} else if(role.toLowerCase().equals("admin")) {
			roles.add(PersonRole.ADMIN);
		}
		
		person.setRoles(roles);
		personRepository.save(person);
		return "Kullanıcıya rol eklendi " + roles;
	}
	
	public String removeRole(String mail, String role) {
		Person person = getPerson(mail).get();
		Set<PersonRole> roles = person.getRoles();
		
		if(role.toLowerCase().equals("user")) {
			roles.remove(PersonRole.USER);
		} else if(role.toLowerCase().equals("admin")) {
			roles.remove(PersonRole.ADMIN);
		}
		
		person.setRoles(roles);
		personRepository.save(person);
		return "Rol kullanıcıdan alındı " + roles;
	}
	
	public Optional<Person> getPerson(String mail) {
		if(!personRepository.findById(mail).isPresent()) return Optional.empty();
		return personRepository.findById(mail);
	}
	public List<FindOwnerResponse> getPersons() {
		List<FindOwnerResponse> list = new ArrayList<>();
		
		for(var i:personRepository.findAll()) {
			FindOwnerResponse response = new FindOwnerResponse();
			response.setEmail(i.getEmail());
			response.setLastName(i.getLastName());
			response.setName(i.getName());
			response.setPhoneNumber(i.getPhoneNumber());
			response.setRoles(i.getRoles().toString());
			list.add(response);
		}
		return list;
	}
	public String addApartmentToPerson(PurchaseApartmentDto dto) {
		if(!apartmentService.getApartment(dto.getBlockNo(), dto.getApartmentNo()).isPresent()) return "Apartman bulunamadı";
		if(getPerson(dto.getEmail()).isPresent() == false) return "Kullanıcı bulunamadı";
		if(apartmentService.getApartment(dto.getBlockNo(), dto.getApartmentNo()).get().getPurchaseDate() != null && apartmentService.getApartment(dto.getBlockNo(), dto.getApartmentNo()).get().getPurchaseDate().length() > 3) return "Daire zaten satın alındı.";
		Optional<Person> person = getPerson(dto.getEmail());
		List<Apartment> list = person.get().getPersonApartments();	
		Optional<Apartment> apartment = apartmentService.getApartment(dto.getBlockNo(), dto.getApartmentNo());
		apartment.get().setPurchaseDate(dto.getPurchaseDate());
		list.add(apartment.get());
		person.get().setPersonApartments(list);
		personRepository.save(person.get());
		apartmentService.saveOrUpdateApartment(apartment.get());
		return "Başarıyla apartmana kullanıcı eklendi";
	}
	
    public List<Apartment> findApartmentsByPerson(String email) {
    	if(getPerson(email).isPresent() == false) return null;
    	List<Apartment> list = getPerson(email).get().getPersonApartments();
        return list;
    }
    
	public FindOwnerResponse findOwnerTheApartment(String blockName, Integer apartmentNo) {
		try {
		if(!apartmentService.getApartment(blockName, apartmentNo).isPresent()) return null;
		if(!getPerson(apartmentService.getApartment(blockName, apartmentNo).get().getEmail()).isPresent()) return null;
		
		Optional<Person> person = getPerson(apartmentService.getApartment(blockName, apartmentNo).get().getEmail());
		FindOwnerResponse response = new FindOwnerResponse();
		response.setEmail(person.get().getEmail());
		response.setLastName(person.get().getLastName());
		response.setName(person.get().getName());
		response.setPhoneNumber(person.get().getPhoneNumber());
		response.setRoles(person.get().getRoles().toString());
			return response;
		} catch (Exception e) {
			return null;
		}
	}
	
	public String removeApartmentToPerson(String email, String blockName, Integer apartmentNo) {
		if(!apartmentService.getApartment(blockName, apartmentNo).isPresent()) return "Apartman bulunamadı";
		if(getPerson(email).isPresent() == false) return "Kullanıcı bulunamadı";
		
		for(var i:feeService.findByPerson(email)) {
			if(i.getStatus() == true) {
				return "Ödenmemiş borçlar nedeniyle daire boşaltılamaz.";
			}
		}
		
		if(apartmentService.getApartment(blockName, apartmentNo).get().getPurchaseDate() == null || apartmentService.getApartment(blockName, apartmentNo).get().getPurchaseDate().length() < 4) return "Bu daire henüz satın alınmadı";
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
		
		return "Kullanıcı apartmandan kaldırıldı";
	}
}
 