package com.batuhan.jpa.stocktracking.dto;

import com.batuhan.jpa.stocktracking.entity.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ProductDto {
	@JsonIgnore
    Integer productId;
    String productName;
    Integer productCountStocks;
    Integer productPrice;
    String productImage;
    Category category;
}
