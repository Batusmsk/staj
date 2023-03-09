package com.batuhan.jpa.stocktracking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public List<Products> getCategoryProducts(String category) {
		List<Products> list = new ArrayList<>();
		for(var i:productService.getProducts()) {
			if(i.getCategory().getCategory().equals(category)) {
				Products products = new Products();
				products = i;
				list.add(products);
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
