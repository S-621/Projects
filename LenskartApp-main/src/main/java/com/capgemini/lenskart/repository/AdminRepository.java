package com.capgemini.lenskart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.lenskart.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	Admin findByEmail(String email);	
}