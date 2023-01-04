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
	@Column(name="productsId")
    @Getter
    Integer id;
    
    @Column(name = "productsName")
    @Getter
    @Setter
    String productsName;
    
    @Column(name = "productsCountStocks")
    @Getter
    @Setter
    Integer productsCountStocks;

	@Override
	public String toString() {
		return "Products [id=" + id + ", productsName=" + productsName + ", productsCountStocks=" + productsCountStocks
				+ "]";
	}

	
}
