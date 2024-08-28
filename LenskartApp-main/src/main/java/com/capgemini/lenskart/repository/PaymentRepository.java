package com.capgemini.lenskart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.lenskart.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
