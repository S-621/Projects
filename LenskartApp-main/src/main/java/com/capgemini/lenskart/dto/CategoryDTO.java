package com.capgemini.lenskart.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

	private int categoryId;
	@NotBlank(message = "CategoryName is manditory")
	private String categoryName;

}
