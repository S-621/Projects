package com.capgemini.lenskart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.lenskart.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Customer findByEmail(String email);
}
