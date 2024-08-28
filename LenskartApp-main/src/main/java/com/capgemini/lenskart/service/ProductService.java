package com.capgemini.lenskart.service;

import java.util.List;

import com.capgemini.lenskart.dto.ProductDTO;

public interface ProductService {
	
	public ProductDTO getById(int id);
	public ProductDTO updateProduct(ProductDTO productDTO);
	public boolean deleteProduct(ProductDTO productDTO);
	public ProductDTO addProduct(ProductDTO productDTO);
	public List<ProductDTO> findAll();
	public List<ProductDTO> getProductByBrand(String brandName);
	String deleteProduct(Integer productId);
}
