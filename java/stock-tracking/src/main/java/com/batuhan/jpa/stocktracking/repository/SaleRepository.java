package com.batuhan.jpa.stocktracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batuhan.jpa.stocktracking.entity.Sales;

public interface SaleRepository extends JpaRepository<Sales, Integer> {

}
