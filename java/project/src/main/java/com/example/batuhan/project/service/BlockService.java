package com.example.batuhan.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.batuhan.project.dto.CreateBlockDto;
import com.example.batuhan.project.entity.Block;
import com.example.batuhan.project.repository.BlockRepository;

@Service
public class BlockService {

	@Autowired
	BlockRepository blockRepository;

	public String createBlock(CreateBlockDto createBlockDto) {
		try {
			for (var i : blockRepository.findAll()) {
				if (i.getBlockName().toLowerCase().equals(createBlockDto.getBlockName().toLowerCase())) {
					return "Bu isimde bir blok mevcut";
				}
			}
			Block block = new Block();
			block.setBlockName(createBlockDto.getBlockName().toUpperCase());
			block.setNumberOfFloors(createBlockDto.getNumberOfFloors());
			block.setTotalApartmentCount(createBlockDto.getTotalApartmentCount());
			block.setBaseArea(createBlockDto.getBaseArea());
			blockRepository.save(block);
			return "Blok oluşturuldu";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String deleteBlock(String blockName) {
		try {
			for(var i:blockRepository.findAll()) {
				if(i.getBlockName().toLowerCase().equals(blockName.toLowerCase())) {
					blockRepository.delete(i);
					return "Blok silindi";
				}
			}
			return "Böyle bir blok bulunamadı";
		} catch(Exception e) {
			return e.getMessage();
		}
	}
	
	public List<Block> getBlocks() {
		return blockRepository.findAll();
	}
	
	public Optional<Block> getBlock(String BlockName) {
		if(blockRepository.findById(BlockName).isPresent()) {
			return blockRepository.findById(BlockName);
		}
		return Optional.empty();
 	}
	
}
