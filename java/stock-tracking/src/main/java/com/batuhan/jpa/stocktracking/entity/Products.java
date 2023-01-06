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
    Integer id;
    
    @Column(name = "productName")
    @Getter
    @Setter
    String productName;
    
    @Column(name = "productCountStocks")
    @Getter
    @Setter
    Integer productCountStocks;
    
    @Column(name = "price")
    @Getter 
    @Setter
    Integer price;

	@Override
	public String toString() {
		return "Products [id=" + id + ", productName=" + productName + ", productCountStocks=" + productCountStocks
				+ ", price=" + price + "]";
	}



	
}
