package com.batuhan.simsek.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batuhan.simsek.jpa.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {}
