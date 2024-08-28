package com.capgemini.lenskart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.lenskart.dto.ProductDTO;
import com.capgemini.lenskart.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/v1/getById")
	public ResponseEntity<ProductDTO> getById(@RequestParam int id) {
		return ResponseEntity.ok(productService.getById(id));
	}

	@PutMapping("/v1/updateProduct")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok(productService.updateProduct(productDTO));
	}

	@DeleteMapping("/v1/deleteProduct")
	public ResponseEntity<Boolean> deleteProduct(@RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok(productService.deleteProduct(productDTO));
	}

	@PostMapping("/v1/addProduct")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok(productService.addProduct(productDTO));
	}

	@GetMapping("/v1/findAll")
	public ResponseEntity<List<ProductDTO>> findAll() {
		return ResponseEntity.ok(productService.findAll());
	}

	@GetMapping("/v1/getProductByBrand")
	public ResponseEntity<List<ProductDTO>> getProductByBrand(@RequestParam String brandName) {
		return ResponseEntity.ok(productService.getProductByBrand(brandName));
	}

	@DeleteMapping("v1/deleteProductById/{productId}")
	public ResponseEntity<String> deleteProductById(@PathVariable("productId") Integer productId) {
		return ResponseEntity.ok(productService.deleteProduct(productId));
	}
}
