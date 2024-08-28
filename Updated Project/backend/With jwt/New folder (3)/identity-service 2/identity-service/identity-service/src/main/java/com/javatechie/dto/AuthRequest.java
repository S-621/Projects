package com.javatechie.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

	@Email
	@NotNull(message = "Email is mandatory")
    private String email;
	
	@NotNull(message = "Password is mandatory")
	@Size(min = 8, message = "minimum 8 characters required")
    private String password;

}
