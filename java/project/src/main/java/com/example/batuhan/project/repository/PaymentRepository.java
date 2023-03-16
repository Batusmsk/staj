package com.example.batuhan.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.batuhan.project.entity.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
