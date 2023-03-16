package com.example.batuhan.project.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Person {
	
	@Id
	private String email;
	private String phoneNumber;
	private String name;
	private String lastName;
	private Integer debt;
	
   @OneToMany
   @JoinColumn(name = "email")
   List<Apartment> personApartments = new ArrayList<>();

}
