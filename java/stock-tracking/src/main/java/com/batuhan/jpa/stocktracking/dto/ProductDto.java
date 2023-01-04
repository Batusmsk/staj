package com.batuhan.jpa.stocktracking.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
	@JsonIgnore
    Integer id;
    String productsName;
    Integer productsCountStocks;
}
