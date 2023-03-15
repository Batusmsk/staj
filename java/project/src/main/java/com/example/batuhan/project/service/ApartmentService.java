package com.example.batuhan.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.batuhan.project.dto.ApartmentDto;
import com.example.batuhan.project.entity.Apartment;
import com.example.batuhan.project.repository.ApartmentRepository;

@Service
public class ApartmentService {
	@Autowired
	ApartmentRepository apartmentRepository;
	@Autowired
	BlockService blockService;
	
	public String createApartment(ApartmentDto apartmentDto) {
		if(!blockService.getBlock(apartmentDto.getBlockName().toUpperCase()).isPresent()) return "No block was found.";
		if((blockService.getBlock(apartmentDto.getBlockName()).get().getNumberOfFloors()) < apartmentDto.getFloor()) return "floor area excess";
		Integer baseArea = apartmentDto.getBaseArea();
		for(var i:aptOnTheFloor(apartmentDto.getBlockName(), apartmentDto.getFloor())) {
			baseArea += i.getBaseArea();
		}
		if(baseArea <= blockService.getBlock(apartmentDto.getBlockName()).get().getBaseArea()) {
			Apartment apartment = new Apartment();
			apartment.setApartmentNo(apartmentDto.getApartmentNo());
			apartment.setBaseArea(apartmentDto.getBaseArea());
			apartment.setFloor(apartmentDto.getFloor());
			apartment.setBlock(blockService.getBlock(apartmentDto.getBlockName().toUpperCase()).get());
			apartmentRepository.save(apartment);
			return "succesfully";
		}
		return "The square meters of the apartments located in the block exceed the square meter of the block.";
	}
	public List<ApartmentDto> aptOnTheFloor(String blockName, Integer floor) {
		List<ApartmentDto> list = new ArrayList<>();
		if(!blockService.getBlock(blockName).isPresent()) return list;
		for(var i:apartmentRepository.findAll()) {
			
			if(i.getBlock().getBlockName().toLowerCase().equals(blockName.toLowerCase()) && i.getFloor().equals(floor)) {
				
				ApartmentDto apartmentDto = new ApartmentDto();
				apartmentDto.setApartmentNo(i.getApartmentNo());
				apartmentDto.setBaseArea(i.getBaseArea());
				apartmentDto.setBlockName(i.getBlock().getBlockName());
				apartmentDto.setFloor(i.getFloor());
				list.add(apartmentDto);				
			}
		}
		return list;
	}
	
	public List<Apartment> getApartments() {
		return apartmentRepository.findAll();
	}
	
	public Optional<Apartment> getApartment(String blockName, Integer apartmentId) {
		for(var i:getApartments()) {
			if(i.getApartmentNo()== apartmentId && i.getBlock().getBlockName().equals(blockName)) {
				return Optional.of(i);
			}
		}
		return Optional.empty();
	}
	public List<Apartment> findApartmentsById(Integer id) {
		List<Apartment> list = new ArrayList<>();
		for(var i:getApartments()) {
			if(i.getApartmentNo() == id) {
				list.add(i);
			}
		}
		return list;
	}
	
	public List<Apartment> findApartmentsByBlockName(String block) {
		List<Apartment> list = new ArrayList<>();
		for(var i:getApartments()) {
			if(i.getBlock().getBlockName().equals(block)) {
				list.add(i);
			}
		}
		return list;
	}
	
	public String deleteApartment(String blockName, Integer apartmentNo) {
		if(getApartment(blockName, apartmentNo).isPresent()) {
			apartmentRepository.delete(getApartment(blockName, apartmentNo).get());
			return "succesfully";
		}
		return "Apartment not found";
	}

}
