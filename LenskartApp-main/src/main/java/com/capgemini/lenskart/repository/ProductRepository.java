package com.capgemini.lenskart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.lenskart.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Product findByProductName(String productName);

	List<Product> findByIsActiveTrue();

	Product findByProductIdAndIsActiveTrue(int id);

	List<Product> findByBrandAndIsActiveTrue(String brandName);
	
}
