package com.cg.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {
	private int categoryId;
	@NotBlank(message = "category name is mandatory")
	private String categoryName;
}
