package com.javatechie.repository;

import com.javatechie.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {
	Optional<UserCredential> findByEmail(String email);

	List<UserCredential> findByRole(String role);
}
