package com.batuhan.jpa.stocktracking.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ProductDto {
	@JsonIgnore
    Integer id;
    String productName;
    Integer productCountStocks;
    Integer productPrice;
}
