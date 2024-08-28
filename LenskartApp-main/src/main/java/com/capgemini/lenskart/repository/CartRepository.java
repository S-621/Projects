package com.capgemini.lenskart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.lenskart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	
}