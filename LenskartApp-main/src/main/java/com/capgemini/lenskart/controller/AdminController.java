package com.capgemini.lenskart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.lenskart.dto.AdminDTO;
import com.capgemini.lenskart.dto.CategoryDTO;
import com.capgemini.lenskart.dto.ProductDTO;
import com.capgemini.lenskart.exception.CustomException;
import com.capgemini.lenskart.service.AdminService;
import com.capgemini.lenskart.service.CategoryService;
import com.capgemini.lenskart.service.ProductService;

@RestController
@RequestMapping("/Admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	
	//Add admin
	@PostMapping("/v1/addAdmin")
	public ResponseEntity<String> addAdmin(@Validated @RequestBody AdminDTO userDTO)throws CustomException {
		return ResponseEntity.ok(adminService.addAdmin(userDTO));
	}
	//Fetch admin by email
	@GetMapping("/v1/getAdminByEmail")
	public ResponseEntity<AdminDTO> getAdmin(@RequestParam String email)throws CustomException {
		return ResponseEntity.ok(adminService.getAdminByEmail(email));
	}
	//Update product by admin
	@PutMapping("/v1/updateProduct")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO)throws CustomException{
		return ResponseEntity.ok(productService.updateProduct(productDTO));
	}
	//Add product by admin
	@PostMapping("/v1/addProduct")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO)throws CustomException {
		return ResponseEntity.ok(productService.addProduct(productDTO));
	}
	//Add category by admin
	@PostMapping("/v1/addCategory")
	public ResponseEntity<String> addCategory(@Validated @RequestBody CategoryDTO categoryDTO)throws CustomException {
		return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
	}
	//Update category by admin
	@PutMapping("/v1/updateCategory")
	public ResponseEntity<String> updateCategory(@RequestBody CategoryDTO categoryDTO)throws CustomException {
		return ResponseEntity.ok(categoryService.updateCategory(categoryDTO));
	}
	//update admin by providing userId
	@PutMapping("v1/updateAdminById/{userId}")
	public ResponseEntity<AdminDTO> updateById(@PathVariable("userId")Integer userId,@RequestBody AdminDTO adminDTO) throws CustomException{
		return ResponseEntity.ok(adminService.updateAdminById(userId, adminDTO));
	}
	//delete admin by providing userId
	@DeleteMapping("v1/deleteById/{userId}")
	public ResponseEntity<String> deleteById(@PathVariable("userId")Integer userId) throws CustomException{
		return ResponseEntity.ok(adminService.deleteAdminById(userId));
	}
	//delete product by providing product id by admin
	@DeleteMapping("v1/deleteProductById/{productId}")
	public ResponseEntity<String> deleteProductById(@PathVariable("productId")Integer productId)throws CustomException{
		return ResponseEntity.ok(adminService.deleteProductById(productId));
	}
	//delete category by providing category id by admin
	@DeleteMapping("v1/deleteCategoryById/{categoryId}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable("categoryId")Integer categoryId)throws CustomException{
		return ResponseEntity.ok(adminService.deleteCategoryById(categoryId));
	}
}
