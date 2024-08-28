package com.capgemini.lenskart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.lenskart.dto.CartDTO;
import com.capgemini.lenskart.entity.Cart;
import com.capgemini.lenskart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/v1/addToCart")
	public ResponseEntity<Cart> addToCart(@Validated @RequestBody CartDTO cartDTO) {
		return ResponseEntity.ok(cartService.addToCart(cartDTO));
	}
	@PutMapping("/v1/updateCart")
	public ResponseEntity<Cart> updateCart(@Validated @RequestBody CartDTO userDTO) {
		return ResponseEntity.ok(cartService.updateCart(userDTO));
	}
	@DeleteMapping("/v1/deleteProductFromCart")
	public ResponseEntity<Cart> deleteProductFromCart(@RequestParam int productId,int cartId ) {
		return ResponseEntity.ok(cartService.deleteProductFromCart(productId,cartId));
	}
}
