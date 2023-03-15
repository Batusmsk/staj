package com.example.batuhan.project.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@IdClass(MyKey.class)
public class Apartment {
	
	@Id
	private Integer apartmentNo;
    
	Integer floor;
	Integer baseArea;
	
	@Id
	//@ManyToOne
    //@JoinColumn(name="blockName", nullable=false)
    @Getter
    @Setter
    private String blockName;
    //private Block block;
}

