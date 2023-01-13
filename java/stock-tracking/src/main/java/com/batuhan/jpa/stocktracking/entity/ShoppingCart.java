package com.batuhan.jpa.stocktracking.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shoppingCart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartId")
    @Getter
	private Integer cartId;
	@Column(name = "cartDate")
    @Getter
    @Setter
	private String cartDate;

	@Getter
	@Setter
	@Column(name = "product")
	@ElementCollection(targetClass=Products.class)
	private List<Products> product;
	
    @ManyToOne
    @JoinColumn(name="customerId", nullable=false)
    @Getter
    @Setter
    Customer customer;
}
