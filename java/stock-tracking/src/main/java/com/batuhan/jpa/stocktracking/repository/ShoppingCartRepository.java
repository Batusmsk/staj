package com.batuhan.jpa.stocktracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batuhan.jpa.stocktracking.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

}
