package com.batuhan.jpa.stocktracking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhan.jpa.stocktracking.dto.ProductDto;
import com.batuhan.jpa.stocktracking.entity.Category;
import com.batuhan.jpa.stocktracking.entity.Products;
import com.batuhan.jpa.stocktracking.repository.CategoryRepository;
@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductService productService;
	public boolean addCategory(String name) {
		if(name.isEmpty()) return false;
		var list = categoryRepository.findAll();
		for(var i:list) {
			if(i.getCategory().equals(name)) {
				return false;
			}
		}

		Category category = new Category();
		category.setCategory(name);
		categoryRepository.save(category);
		return true;
	}
	
	
	public List<ProductDto> getCategoryProducts(String category) {
		List<ProductDto> list = new ArrayList<>();
		String str = category;
		
		try {
		    int num = Integer.parseInt(str);
			if(!getCategory(num).isPresent()) {
				list.add(null);
				return list;
			}
		} catch (NumberFormatException e) {
			if(!getCategory(category).isPresent()) {
				list.add(null);
				return list;
			}
		}
		

		
		for(var i:productService.getProducts()) {
			if(i.getCategory().getCategory().equals(category) || String.valueOf(i.getCategory().getId()).equals(category)) {
				ProductDto productDto = new ProductDto();
				productDto.setProductCountStocks(i.getProductCountStocks());
				productDto.setProductName(i.getProductName());
				productDto.setProductId(i.getProductId());
				productDto.setProductPrice(i.getProductPrice());
				productDto.setCategory(i.getCategory());
				list.add(productDto);
			} else {
				System.err.println("xxx");
			}
		}
		
		return list;
	}
	
	public boolean deleteCategory(String name) {
		Optional<Category> category = categoryRepository.findByCategory(name);
		categoryRepository.deleteById(category.get().getId());
		return true;
	}
	public List<Category> getCategoryList() {
		return categoryRepository.findAll();	
		}
	
	public Optional<Category> getCategory(String name) {
		Optional<Category> category = categoryRepository.findByCategory(name);
		return category;
	}
	public Optional<Category> getCategory(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		return category;
	}
}
