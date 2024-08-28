package com.capgemini.lenskart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.lenskart.dto.CategoryDTO;
import com.capgemini.lenskart.entity.Category;
import com.capgemini.lenskart.service.CategoryService;

@RestController
@RequestMapping("/Category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/v1/addCategory")
	public ResponseEntity<String> addCategory(@Validated @RequestBody CategoryDTO categoryDTO) {
		return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
	}

	@DeleteMapping("/v1/removeCategory")
	public ResponseEntity<String> removeCategory(@RequestParam int categoryId) {
		return ResponseEntity.ok(categoryService.removeCategory(categoryId));
	}

	@PutMapping("/v1/updateCategory")
	public ResponseEntity<String> updateCategory(@RequestBody CategoryDTO categoryDTO) {
		return ResponseEntity.ok(categoryService.updateCategory(categoryDTO));
	}

	@GetMapping("/v1/searchCategoryByName")
	public ResponseEntity<CategoryDTO> searchCategoryByName(@RequestParam String categoryName) {
		return ResponseEntity.ok(categoryService.searchCategoryByName(categoryName));
	}

	@GetMapping("/v1/searchCategoryById")
	public ResponseEntity<CategoryDTO> searchCategoryById(@RequestParam int categoryId) {
		return ResponseEntity.ok(categoryService.searchCategoryById(categoryId));
	}
	@GetMapping("/v1/findAllCategory")
	public ResponseEntity<List<Category>> findAllCategory() {
		return ResponseEntity.ok(categoryService.findAllCategory());
	}

}
