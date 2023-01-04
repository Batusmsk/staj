package com.batuhan.jpa.stocktracking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhan.jpa.stocktracking.dto.ProductDto;
import com.batuhan.jpa.stocktracking.entity.Products;
import com.batuhan.jpa.stocktracking.repository.ProductsRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductsRepository productsRepository;
	public Boolean createProduct(ProductDto productDto) { 
		Products product = new Products();
		product.setProductsName(productDto.getProductsName());
		product.setProductsCountStocks(productDto.getProductsCountStocks());
		var ret=productsRepository.save(product);
		return true;
	}
	
	public boolean deleteProduct(Integer id) {
		if(!productsRepository.findById(id).isPresent()) return false;
		productsRepository.deleteById(id);
		return true;	
	}	
	public boolean setProduct(Integer id, Integer count, String name) {
		Optional<Products> product = productsRepository.findById(id);
		if(!product.isPresent()) return false;
		if(id < 0) return false;
		product.get().setProductsCountStocks(count);
		//System.err.print(count);
		if(name != null) product.get().setProductsName(name);
		productsRepository.save(product.get());
		
		return true;
	}
	public Optional<Products> getProduct(Integer id) {
		return productsRepository.findById(id);
	}
	public List<Products> getProducts() {
		return productsRepository.findAll();
	}

}
