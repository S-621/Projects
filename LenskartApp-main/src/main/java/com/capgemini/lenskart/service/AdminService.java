package com.capgemini.lenskart.service;

import com.capgemini.lenskart.dto.AdminDTO;
import com.capgemini.lenskart.dto.CategoryDTO;
import com.capgemini.lenskart.dto.ProductDTO;
import com.capgemini.lenskart.dto.SignInDto;
import com.capgemini.lenskart.response.CommonResponse;

public interface AdminService {

	public String addAdmin(AdminDTO adminDTO);

	public AdminDTO getAdminByEmail(String email);
	
	public AdminDTO updateAdmin(AdminDTO adminDTO);

	public ProductDTO updateProduct(ProductDTO productDTO);
	
	public ProductDTO addProduct(ProductDTO productDTO);
	
	public String addCategory(CategoryDTO category);
	
	public String updateCategory(CategoryDTO category);
	
	public AdminDTO updateAdminById(Integer userId,AdminDTO adminDTO);
	
	public String deleteAdminById(Integer userId);
	
	public String deleteProductById(Integer productId);
	
	public String deleteCategoryById(Integer categoryId);
	
	public CommonResponse signIn(SignInDto signRequestDTO);
}
