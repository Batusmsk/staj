package com.batuhan.jpa.stocktracking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
  
import com.batuhan.jpa.stocktracking.dto.ProductDto;
import com.batuhan.jpa.stocktracking.dto.SoldOutDto;
import com.batuhan.jpa.stocktracking.entity.Employees;
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
	
	public Boolean createProduct(ProductDto productDto) { 
		Products product = new Products();
		product.setProductName(productDto.getProductName());
		product.setProductPrice(productDto.getProductPrice());
		product.setProductCountStocks(productDto.getProductCountStocks());
		var ret=productsRepository.save(product);
		return true;
	}
	
	public boolean deleteProduct(Integer id) {  
		if(!productsRepository.findById(id).isPresent()) return false;
		productsRepository.deleteById(id);
		return true;	
	}	
	
	public boolean setProduct(Integer id, Integer count, String name, Integer price) {
		Optional<Products> product = productsRepository.findById(id);
		if(!product.isPresent()) return false;
		if(id < 0) return false;
		product.get().setProductCountStocks(count);
		product.get().setProductPrice(price);
		//System.err.print(count);
		if(name != null) product.get().setProductName(name);
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
	public Optional<Products> getProduct(Integer id) {
		return productsRepository.findById(id);
	}
	public List<Products> getProducts() {
		return productsRepository.findAll();
	}

}
