package com.batuhan.jpa.stocktracking.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerId")
    @Getter
	private Integer customerId;
    @Column(name = "fullName")
    @Getter
    @Setter
	private String fullName;
    @Getter
    @Setter
    @Column(name = "createDate")
	private String createDate;

    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @OrderBy("id ASC")
    @Getter
	private List<ShoppingCart> ShoppingCart;

}
