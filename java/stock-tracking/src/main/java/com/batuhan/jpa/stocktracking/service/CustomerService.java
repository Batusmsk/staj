package com.batuhan.jpa.stocktracking.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhan.jpa.stocktracking.dto.CreateSaleDto;
import com.batuhan.jpa.stocktracking.dto.CustomerDto;
import com.batuhan.jpa.stocktracking.dto.SaleDto;
import com.batuhan.jpa.stocktracking.dto.ShoppingCartDto;
import com.batuhan.jpa.stocktracking.entity.Customer;
import com.batuhan.jpa.stocktracking.entity.Products;
import com.batuhan.jpa.stocktracking.entity.ShoppingCart;
import com.batuhan.jpa.stocktracking.repository.CustomerRepository;
import com.batuhan.jpa.stocktracking.repository.ShoppingCartProductRepository;
import com.batuhan.jpa.stocktracking.repository.ShoppingCartRepository;
import com.batuhan.jpa.stocktracking.request.CreateCartRequest;
import com.batuhan.jpa.stocktracking.request.CreateCustomerRequest;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	@Autowired
	ShoppingCartProductRepository shoppingCartProductRepository;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	ProductService productService;

	public boolean createCustomer(CreateCustomerRequest input) {
		SimpleDateFormat d = new SimpleDateFormat();
		Date date = new Date();
		Customer customer = new Customer();
		customer.setFullName(input.getFullName());
		customer.setCreateDate(d.format(date));
		customerRepository.save(customer);
		return true;
	}

	public Optional<Customer> getCustomer(Integer id) {
		return customerRepository.findById(id);
	}
	
	public List<CustomerDto> getCustomers() {
		List<CustomerDto> customerList = new ArrayList<>();
		for(var customer : customerRepository.findAll()) {
			CustomerDto customerDto = new CustomerDto();
			customerDto.setCreateDate(customer.getCreateDate());
			customerDto.setFullName(customer.getFullName());
			customerDto.setShoppingCart(customer.getShoppingCart());
			customerList.add(customerDto);
		}
		return customerList;
	}

	public List<ShoppingCartDto> getCustomerCart(Integer customerId, String date) {
		List<ShoppingCartDto> cartList = new ArrayList<>();
		if (!getCustomer(customerId).isPresent())
			return null;
		var shoppingCarts = getCustomer(customerId).get().getShoppingCart();
		for (var item : shoppingCarts) {
			ShoppingCartDto shoppingCartDto = new ShoppingCartDto();

			if (!date.equals("null") && null != date)
				if (!item.getCartDate().contains(date))
					continue;

			shoppingCartDto.setCartDate(item.getCartDate());
			shoppingCartDto.setCartId(item.getCartId());
			cartList.add(shoppingCartDto);
		}
		;
		return cartList;
	}

	public List<Products> getCustomerCartProductsget(Integer customerId, Integer id, String date) {
		List<Products> productList = new ArrayList<>();
		if (getCustomer(customerId).isPresent()) {
			if (id >= 1) {
				customerRepository.findById(customerId).get().getShoppingCart().forEach(s -> {
					if (s.getCartId() == id) {
						productList.addAll(s.getProduct());
					}
				});
			} else if (null != date) {
				customerRepository.findById(customerId).get().getShoppingCart().forEach(s -> {
					if (s.getCartDate().contains(date)) {
						productList.addAll(s.getProduct());
					}
				});
			} else {
				productList.addAll(null);
			}
			return productList;
		} else {
			return null;
		}
	}

	public boolean createCart(CreateCartRequest input) {
		SimpleDateFormat d = new SimpleDateFormat();
		Date date = new Date();
		ShoppingCart shoppingCart = new ShoppingCart();
		CreateSaleDto createSaleDto = new CreateSaleDto();
		List<SaleDto> saleList = new ArrayList<>();

		if (!getCustomer(input.getCustomerId()).isPresent())
			return false;
		if (!employeeService.getEmployee(input.getEmployeeId()).isPresent())
			return false;

		shoppingCart.setCustomer(getCustomer(input.getCustomerId()).get());
		shoppingCart.setCartDate(d.format(date));
		shoppingCart.setProduct(input.getProduct());
		var product = input.getProduct();
		for (var item : product) {
			SaleDto sss = new SaleDto();
			if (productService.getProduct(item.getProductId()).get().getProductCountStocks() < 1)
				return false;
			sss.setProductId(item.getProductId());
			saleList.add(sss);
		}
		
		createSaleDto.setSales(saleList);
		createSaleDto.setEmployeeId(input.getEmployeeId());
		
		employeeService.createSale(createSaleDto);
		shoppingCartRepository.save(shoppingCart);
		return true;
	}
}
