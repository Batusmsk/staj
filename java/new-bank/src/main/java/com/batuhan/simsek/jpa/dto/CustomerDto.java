package com.batuhan.simsek.jpa.dto;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
	
	@JsonIgnore
	private Integer customerId;
	
	//@Min(value = 100,message="100 den buyuk olsun")
	private Integer customerIdentityNo;
	 private String customerFullName;

}