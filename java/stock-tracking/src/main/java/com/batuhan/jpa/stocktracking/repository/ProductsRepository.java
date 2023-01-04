package com.batuhan.jpa.stocktracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batuhan.jpa.stocktracking.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer>{

}
