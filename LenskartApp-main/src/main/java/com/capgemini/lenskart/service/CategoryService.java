package com.capgemini.lenskart.service;

import java.util.List;

import com.capgemini.lenskart.dto.CategoryDTO;
import com.capgemini.lenskart.entity.Category;

public interface CategoryService {
	
	public String addCategory(CategoryDTO category);
	public String removeCategory(int categoryId);
	public String updateCategory(CategoryDTO category);
	public CategoryDTO searchCategoryByName(String name);
	public CategoryDTO searchCategoryById(int id);
	public List<Category> findAllCategory();
	

}
