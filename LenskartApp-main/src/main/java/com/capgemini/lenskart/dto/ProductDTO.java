package com.capgemini.lenskart.dto;

import com.capgemini.lenskart.entity.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private int productId;
	@NotBlank(message = "ProductName is required")
	private String productName;
	@Min(value = 0, message = "productPrice must be greater than 0")
	private double productPrice;
	private String productImage;
	@Min(value = 0, message = "product quantity must be greater than 0")
	private int quantity;
	private Category category;
	private String brand;

}
