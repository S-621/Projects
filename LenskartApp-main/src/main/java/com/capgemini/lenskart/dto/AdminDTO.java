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
public class AdminDTO extends UserDTO {
	@NotBlank(message = "FirstName is Manditory")
	private String firstName;
	@NotBlank(message = "LastName is Manditory")
	private String lastName;
	@NotBlank(message = "Role is Manditory")
	private String role = "Admin";
	private Long number;
	private String address;

}
