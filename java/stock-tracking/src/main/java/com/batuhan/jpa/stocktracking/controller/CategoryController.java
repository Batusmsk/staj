package com.batuhan.jpa.stocktracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batuhan.jpa.stocktracking.dto.ProductDto;
import com.batuhan.jpa.stocktracking.entity.Category;
import com.batuhan.jpa.stocktracking.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
    @PostMapping(value = "/category/{name}")
	public boolean createCategory(@PathVariable("name") String name){
		return categoryService.addCategory(name);
	}
    
    @DeleteMapping(value = "/category/{name}")
	public boolean deleteCategory(@PathVariable("name") String name){
		return categoryService.deleteCategory(name);
	}
    @GetMapping(value = "/category/{name}")
    public List<ProductDto> getCategoryProduct(@PathVariable("name") String category) {
    	return categoryService.getCategoryProducts(category);
    }
    
    @GetMapping(value = "/category")
    public List<Category> getCategoryList() {
    	return categoryService.getCategoryList();
    }
    
}
