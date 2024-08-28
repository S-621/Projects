package com.javatechie.dto;

import com.javatechie.entity.UserCredential;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	
	@NotBlank
	private UserCredential user;
	
	private String token;
}
