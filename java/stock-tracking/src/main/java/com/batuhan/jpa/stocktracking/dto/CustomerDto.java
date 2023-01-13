package com.batuhan.jpa.stocktracking.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.batuhan.jpa.stocktracking.entity.ShoppingCart;

import lombok.Getter;
import lombok.Setter;

public class CustomerDto {

	private Integer customerId;
	@Getter
	@Setter
	private String fullName;
	@Getter
	@Setter
	private String createDate;
	@Getter
	@Setter
	private List<ShoppingCart> ShoppingCart;
}
