package com.batuhan.jpa.stocktracking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhan.jpa.stocktracking.dto.ProductDto;
import com.batuhan.jpa.stocktracking.dto.SoldOutDto;
import com.batuhan.jpa.stocktracking.entity.Category;
import com.batuhan.jpa.stocktracking.entity.Products;
import com.batuhan.jpa.stocktracking.repository.EmployeeRepository;
import com.batuhan.jpa.stocktracking.repository.ProductsRepository;
import com.batuhan.jpa.stocktracking.repository.SaleRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductsRepository productsRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	SaleRepository saleRepository;
	@Autowired
	CategoryService categoryService;
	
	public Boolean createProduct(ProductDto productDto) { 
		Optional<Category> category = categoryService.getCategory(productDto.getCategory().getCategory());
		Products product = new Products();
		product.setProductName(productDto.getProductName());
		product.setProductPrice(productDto.getProductPrice());
		product.setProductCountStocks(productDto.getProductCountStocks());
		product.setCategory(category.get());
		product.setProductImage(productDto.getProductImage());
		var ret=productsRepository.save(product);
		return true;
	}
	
	public boolean deleteProduct(Integer id) {  
		if(!productsRepository.findById(id).isPresent()) return false;
		productsRepository.deleteById(id);
		return true;	
	}	
	
	public boolean setProduct(Integer id, Integer count, String name, Integer price, String category, String image) {
		Optional<Products> product = productsRepository.findById(id);
		if(!product.isPresent()) return false;
		if(id < 0) return false;
		product.get().setProductCountStocks(count == null || count < 0 ? product.get().getProductCountStocks() : count);
		product.get().setProductPrice(price == null || price < 0 ? product.get().getProductPrice() : price);
		product.get().setProductName(name == null || name.equals("null") ? product.get().getProductName() : name);
		product.get().setCategory(category == null || category.equals("null") ? product.get().getCategory() : categoryService.getCategory(category).get());
		product.get().setProductImage(image == null || image.equals("null") ? product.get().getProductImage() : image);
		productsRepository.save(product.get());
		
		return true;
	}
	
	public List<SoldOutDto> getAllSoldProducts() {
		List<SoldOutDto> saleList = new ArrayList<>();
		saleRepository.findAll().forEach(s-> { 
			SoldOutDto soldOutDto = new SoldOutDto();
			soldOutDto.setSaleId(s.getSaleId());
			soldOutDto.setSaleDate(s.getSaleDate());
			soldOutDto.setProductId(s.getProductId());
			soldOutDto.setProductPrice(s.getProductPrice());
			soldOutDto.setEmployeeId(s.getEmployees().getEmployeeId());
			soldOutDto.setCategoryName(getProduct(s.getProductId()).get().getCategory().getCategory());
			saleList.add(soldOutDto);
			});
		return saleList;
	}
	
	public boolean productStockUpdate(Integer id, Integer count) {
		Optional<Products> product = productsRepository.findById(id);
		if(!product.isPresent()) return false;
		//System.err.print(count);
		if(count < 0)  {
			if(product.get().getProductCountStocks() < 1) return false;
			if(count > product.get().getProductCountStocks()) return false;
			product.get().setProductCountStocks(product.get().getProductCountStocks() + count);
			productsRepository.save(product.get());
			return true;
		} else {
			product.get().setProductCountStocks(product.get().getProductCountStocks() + count);
			productsRepository.save(product.get());
			return true;
		}
		
		
	}
	public List<Products> findByName(String name) {
		List<Products> list = new ArrayList<>();
		for(var i:getProducts()) {
			if(i.getProductName().toLowerCase().contains(name.toLowerCase())) {
				Products product = new Products();
				product = i;
				list.add(product);
			}
		}
		return list;
	}
	
	public Optional<Products> getProduct(Integer id) {
		return productsRepository.findById(id);
	}
	public List<Products> getProducts() {
		return productsRepository.findAll();
	}

}
