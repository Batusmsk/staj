package com.batuhan.jpa.stocktracking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="productId")
    @Getter
    Integer productId;
    
    @Column(name = "productName")
    @Getter
    @Setter
    String productName;
    
    @Column(name = "productCountStocks")
    @Getter
    @Setter
    Integer productCountStocks;
    
    @Column(name = "productPrice")
    @Getter 
    @Setter
    Integer productPrice;

	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productName=" + productName + ", productCountStocks="
				+ productCountStocks + ", productPrice=" + productPrice + "]";
	}


	
}
