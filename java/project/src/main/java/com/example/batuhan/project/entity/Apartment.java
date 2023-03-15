package com.example.batuhan.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
@IdClass(MyKey.class)
public class Apartment {
	
	@Id
	private Integer apartmentNo;
    
	Integer floor;
	Integer baseArea;
	
	@Id
	@OneToOne
    //private String blockName;
    private Block block;
}

