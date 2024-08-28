package com.capgemini.lenskart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	@NotBlank(message = "productName is needed")
	private String productName;

	@Min(value = 0, message = "productPrice must be greater than 0")
	private double productPrice;

	@NotBlank(message = "productImage url is needed")
	private String productImage;

	private int quantity;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;

	private String brand;
	
	private Boolean isActive = true;

}
