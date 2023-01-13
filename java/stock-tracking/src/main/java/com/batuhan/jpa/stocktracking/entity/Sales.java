package com.batuhan.jpa.stocktracking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="saleId")
    @Getter
	Integer saleId;
    @Getter
    @Setter
	String saleDate;
    @Getter
    @Setter
    Integer productPrice;
    @Getter
    @Setter
	Integer productId;
	
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    Employees employees;

	@Override
	public String toString() {
		return "Sales [saleId=" + saleId + ", date=" + saleDate + ", productPrice=" + productPrice + ", productId="
				+ productId + ", employees=" + employees + "]";
	}


}
