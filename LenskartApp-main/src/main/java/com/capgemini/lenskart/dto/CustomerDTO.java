package com.capgemini.lenskart.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerDTO extends UserDTO {
	
	@NotBlank(message = "first name is mandatory")
	private String firstName;
	@NotBlank(message = "last name is mandatory")
	private String lastName;
	private String role = "Customer";
	private Long number;
	private String address;

}
