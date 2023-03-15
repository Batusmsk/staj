package com.example.batuhan.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.batuhan.project.dto.CreateBlockDto;
import com.example.batuhan.project.entity.Block;
import com.example.batuhan.project.service.BlockService;

@RestController
public class BlockController {
	@Autowired
	BlockService blockService;
	
	@PostMapping(value = "/block/createBlock")
	public String createBlock(@RequestBody CreateBlockDto block) {
		return blockService.createBlock(block);
	}
	@DeleteMapping(value="/block/deleteBlock/{blockName}")
	public String deleteBlock(@PathVariable("blockName") String blockName) {
		return blockService.deleteBlock(blockName);
	}
	@GetMapping(value = "/block/getBlocks")
	public List<Block> getBlocks() {
		return blockService.getBlocks();
	}
	@GetMapping(value="/block/getBlock/{blockName}")
	public Optional<Block> getBlock(@PathVariable("blockName") String blockName) {
		return blockService.getBlock(blockName);
	}
}
