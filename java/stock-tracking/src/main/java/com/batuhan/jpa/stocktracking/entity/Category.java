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
@Table(name = "categorys")
public class Category {
	@Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryId")
	Integer id;
	@Getter
	@Setter
	String category;
	
}
