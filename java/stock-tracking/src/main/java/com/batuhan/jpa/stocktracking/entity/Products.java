package com.batuhan.jpa.stocktracking.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
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
    @Getter
    @Setter
    String productImage;
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @OrderBy("id ASC")
	Category category;
	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productName=" + productName + ", productCountStocks="
				+ productCountStocks + ", productPrice=" + productPrice + "]";
	}


	
}
