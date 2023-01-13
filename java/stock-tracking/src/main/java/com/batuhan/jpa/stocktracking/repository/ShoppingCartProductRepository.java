package com.batuhan.jpa.stocktracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batuhan.jpa.stocktracking.entity.ShoppingCart;

public interface ShoppingCartProductRepository extends JpaRepository<ShoppingCart, Integer>{

}
