package com.batuhan.jpa.stocktracking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.batuhan.jpa.stocktracking.dto.ShoppingCartDto;
import com.batuhan.jpa.stocktracking.entity.Products;
import com.batuhan.jpa.stocktracking.request.CreateCartRequest;
import com.batuhan.jpa.stocktracking.request.CreateCustomerRequest;
import com.batuhan.jpa.stocktracking.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
    @PostMapping(value = "/customer")
	public boolean createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
		return customerService.createCustomer(createCustomerRequest);
	}
    @PostMapping(value = "/cart/create")
	public boolean createCart(@RequestBody @Valid CreateCartRequest createCartRequest){
    	
		return customerService.createCart(createCartRequest);
	}
    @GetMapping(value = "/customer/{customerId}/{date}")
	public List<ShoppingCartDto> getCustomerCart(@PathVariable("customerId") Integer id, @PathVariable("date") String date){
		return customerService.getCustomerCart(id, date);
	}
    
    @GetMapping("/cart/{customerId}/{cartId}/{date}")
    public List<Products> getCustomerCartProductsget(@PathVariable("customerId") Integer customerId, @PathVariable("cartId")Integer id,@PathVariable("date") String date) {
    	return customerService.getCustomerCartProductsget(customerId, id, date);
    }
}
