package com.capgemini.lenskart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import com.capgemini.lenskart.dto.CartDTO;

import com.capgemini.lenskart.entity.Cart;
import com.capgemini.lenskart.entity.Customer;
import com.capgemini.lenskart.entity.Product;

import com.capgemini.lenskart.repository.CartRepository;

import com.capgemini.lenskart.repository.CustomerRepository;

import com.capgemini.lenskart.repository.ProductRepository;

import com.capgemini.lenskart.service.impl.CartServiceImpl;

@ExtendWith(MockitoExtension.class)

class CartServiceImplTest {

	@Mock

	private ProductRepository productRepository;

	@Mock

	private CartRepository cartRepository;

	@Mock

	private CustomerRepository customerRepository;

	@InjectMocks

	private CartServiceImpl cartService;

	@Test

	void testAddToCart() {

		// Mocking data

		CartDTO cartDTO = new CartDTO();

		cartDTO.setUserid(1);

		cartDTO.setProductId(Arrays.asList(1, 2, 3));

		Customer user = new Customer();

		user.setUserid(1);

		Product product1 = new Product();

		product1.setProductId(1);

		product1.setProductPrice(20.0);

		Product product2 = new Product();

		product2.setProductId(2);

		product2.setProductPrice(30.0);

		Product product3 = new Product();

		product3.setProductId(3);

		product3.setProductPrice(25.0);

		when(customerRepository.findById(1)).thenReturn(Optional.of(user));

		when(productRepository.findByProductIdAndIsActiveTrue(1)).thenReturn(product1);

		when(productRepository.findByProductIdAndIsActiveTrue(2)).thenReturn(product2);

		when(productRepository.findByProductIdAndIsActiveTrue(3)).thenReturn(product3);

		// Test the method

		Cart resultCart = cartService.addToCart(cartDTO);

		// Assertions

		assertNotNull(resultCart);

		assertEquals(3, resultCart.getProductInCart().size());

		assertEquals(75.0, resultCart.getTotalPrice());

	}

}
