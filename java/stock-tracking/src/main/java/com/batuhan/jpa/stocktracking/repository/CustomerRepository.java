package com.batuhan.jpa.stocktracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batuhan.jpa.stocktracking.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
