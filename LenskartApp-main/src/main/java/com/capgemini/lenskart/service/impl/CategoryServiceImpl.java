package com.capgemini.lenskart.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lenskart.dto.CategoryDTO;
import com.capgemini.lenskart.entity.Category;
import com.capgemini.lenskart.exception.CustomException;
import com.capgemini.lenskart.repository.CategoryRepository;
import com.capgemini.lenskart.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String addCategory(CategoryDTO categoryDTO)throws CustomException {
		Category category = new Category();

		Category dbCategory = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
		if (dbCategory != null) {
			throw new CustomException("Category already Found!!");
		}
		category = modelMapper.map(categoryDTO, Category.class);
		Category saveCategory = categoryRepository.save(category);
		categoryDTO.setCategoryId(saveCategory.getCategoryId());
		return "Category Added!!";
	}

	@Override
	public String removeCategory(int categoryId) {

		Optional<Category> dbCategory = categoryRepository.findById(categoryId);

		if (dbCategory.isEmpty()) {
			throw new CustomException("Category id is not found: " + categoryId);
		}
		categoryRepository.delete(dbCategory.get());

		return "Category deleted!!";
	}

	@Override
	public String updateCategory(CategoryDTO categoryDTO)throws CustomException {
		Optional<Category> dbCategory = categoryRepository.findById(categoryDTO.getCategoryId());

		if (dbCategory.isEmpty()) {
			throw new CustomException("Category id is not found: " + categoryDTO.getCategoryId());
		}
		Category category = dbCategory.get();
		category = modelMapper.map(categoryDTO, Category.class);
		categoryRepository.save(category);
		return "Category updated!!";
	}

	@Override
	public CategoryDTO searchCategoryByName(String name) {
		CategoryDTO categoryDTO = new CategoryDTO();
		Category dbCategory = categoryRepository.findByCategoryName(name);
		if (dbCategory == null) {
			throw new CustomException("Category is Not Found!!");
		}
		categoryDTO = modelMapper.map(dbCategory, CategoryDTO.class);
		return categoryDTO;
	}

	@Override
	public CategoryDTO searchCategoryById(int categoryId) {
		CategoryDTO categoryDTO = new CategoryDTO();
		Optional<Category> dbCategory = categoryRepository.findById(categoryId);

		if (dbCategory.isEmpty()) {
			throw new CustomException("Category id is not found: " + categoryId);
		}
		categoryDTO = modelMapper.map(dbCategory.get(), CategoryDTO.class);
		return categoryDTO;
	}

	@Override
	public List<Category> findAllCategory() {
		List<Category> findAllCategory = categoryRepository.findAll();
		return findAllCategory;
	}

}
