package com.batuhan.jpa.stocktracking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.batuhan.jpa.stocktracking.dto.ProductDto;
import com.batuhan.jpa.stocktracking.dto.SoldOutDto;
import com.batuhan.jpa.stocktracking.entity.Products;
import com.batuhan.jpa.stocktracking.service.ProductService;

@RestController
public class adminController {
	
	@Autowired
	ProductService productService;
    @PostMapping(value = "/admin/product")
	public boolean createProduct(@RequestBody ProductDto productDto){
		return productService.createProduct(productDto);
	}
    @GetMapping(value = "/admin/products")
    public List<Products> getProducts() {
    	return productService.getProducts();
    }
    @GetMapping(value = "/admin/product/soldOut") 
    public List<SoldOutDto> getAllSoldProducts() {
    	 return productService.getAllSoldProducts();
    }
    @GetMapping(value = "/admin/product/{productId}")
    public Optional<Products> getProduct(@PathVariable("productId") Integer id) { 
		return productService.getProduct(id);
    }
    @DeleteMapping(value = "/admin/product/{productId}")
    public boolean deleteProduct(@PathVariable("productId") Integer id) { 
		return productService.deleteProduct(id);
    }
    @PutMapping(value = "/admin/product/set/{productId}/{count}/{name}/{price}")
    public boolean deleteProduct(@PathVariable("productId") Integer id, @PathVariable("count") Integer count, @PathVariable("name") String name, @PathVariable("price") Integer price) { 
		return productService.setProduct(id,count,name, price);
    }
}
