package com.capgemini.lenskart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.lenskart.entity.User;

public interface RegistrationRepository extends JpaRepository<User, Integer>{
	
}