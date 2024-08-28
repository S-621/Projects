package com.capgemini.lenskart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.lenskart.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	
}

