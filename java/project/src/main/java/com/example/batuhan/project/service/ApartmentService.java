package com.example.batuhan.project.service;

import java.util.ArrayList;
import java.util.List;

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
			apartment.setBlockName(apartmentDto.getBlockName());
			apartmentRepository.save(apartment);
			return "succesfully";
		}
		return "The square meters of the apartments located in the block exceed the square meter of the block.";

	}
	public List<ApartmentDto> aptOnTheFloor(String blockName, Integer floor) {
		List<ApartmentDto> list = new ArrayList<>();
		if(!blockService.getBlock(blockName).isPresent()) return list;
		for(var i:apartmentRepository.findAll()) {
			
			if((blockService.getBlock(blockName).get().getNumberOfFloors()) <= floor && i.getFloor().equals(floor)) {
				
				ApartmentDto apartmentDto = new ApartmentDto();
				apartmentDto.setApartmentNo(i.getApartmentNo());
				apartmentDto.setBaseArea(i.getBaseArea());
				apartmentDto.setBlockName(blockService.getBlock(i.getBlockName()).get().getBlockName());
				apartmentDto.setFloor(i.getFloor());
				list.add(apartmentDto);				
			}
		}
		return list;
	}
	
	public List<Apartment> getApartments() {
		return apartmentRepository.findAll();
	}

}
