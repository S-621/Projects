package com.capgemini.lenskart.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {
	@NotBlank(message = "email is mandatory")
	@Email
	private String email;
	
	@NotEmpty
	@Size(min = 8, message = "password should have at least 8 characters")
	private String password;
}
