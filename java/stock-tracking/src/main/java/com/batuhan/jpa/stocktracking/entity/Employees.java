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
@Table(name = "employees")
@Setter
@Getter
public class Employees {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_id")

    Integer employeeId;

	@Column(name = "fullName")
	String fullName;

	@Column(name = "wage")
	Integer wage;
	

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employees")
    @OrderBy("id ASC")
    private List<Sales> sales;
	
	@Override
	public String toString() {
		return "Employees [employeeId=" + employeeId + ", fullName=" + fullName + ", wage=" + wage + "]";
	}
	
}
