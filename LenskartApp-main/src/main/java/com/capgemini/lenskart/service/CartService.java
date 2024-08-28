package com.capgemini.lenskart.service;

import com.capgemini.lenskart.dto.CartDTO;
import com.capgemini.lenskart.entity.Cart;

public interface CartService {

	public Cart addToCart(CartDTO cartDTO);

	public Cart updateCart(CartDTO cartDTO);

	// public String deleteProductFromCart(ProductDTO productDTO);
	public Cart deleteProductFromCart(int productId, int cartId);

}
