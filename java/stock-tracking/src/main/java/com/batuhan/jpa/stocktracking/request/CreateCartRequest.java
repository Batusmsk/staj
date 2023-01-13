package com.batuhan.jpa.stocktracking.request;

import java.util.List;

import javax.validation.constraints.Min;

import com.batuhan.jpa.stocktracking.entity.Products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCartRequest {
	@Min(value=1,message=" employeeId Daha buyuk gir")
	private Integer employeeId;
	@Min(value=1,message="customerId Daha buyuk gir")
	private Integer customerId;
	private List<Products> product;
	
}
