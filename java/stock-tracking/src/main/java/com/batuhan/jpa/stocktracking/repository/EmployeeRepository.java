package com.batuhan.jpa.stocktracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batuhan.jpa.stocktracking.entity.Employees;

public interface EmployeeRepository extends JpaRepository<Employees, Integer>{

}
