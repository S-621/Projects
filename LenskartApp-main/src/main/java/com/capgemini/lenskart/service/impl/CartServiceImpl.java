package com.capgemini.lenskart.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lenskart.dto.CartDTO;
import com.capgemini.lenskart.entity.Cart;
import com.capgemini.lenskart.entity.Customer;
import com.capgemini.lenskart.entity.Product;
import com.capgemini.lenskart.exception.CustomException;
import com.capgemini.lenskart.repository.CartRepository;
import com.capgemini.lenskart.repository.CustomerRepository;
import com.capgemini.lenskart.repository.ProductRepository;
import com.capgemini.lenskart.service.CartService;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional
	public Cart addToCart(CartDTO cartDTO) {
	    Optional<Customer> optCustomer = customerRepository.findById(cartDTO.getUserid());
	    if (!optCustomer.isPresent()) {
	        throw new CustomException("User Not Found!!");
	    }

	    Customer user = optCustomer.get();
	    Cart cart = (user.getCart() != null) ? user.getCart() : new Cart();
	    
	    // Initialize productInCart if it is null
	    Set<Product> productInCart = (cart.getProductInCart() != null) ? cart.getProductInCart() : new HashSet<>();
	    
	    List<Integer> productIds = cartDTO.getProductId();
	    double totalCost = 0.0;

	    for (Integer productId : productIds) {
	        Product dbProduct = productRepository.findByProductIdAndIsActiveTrue(productId);
	        if (dbProduct == null) {
	            throw new CustomException("Product Not Found!!");
	        }
	        productInCart.add(dbProduct);
	        totalCost += dbProduct.getProductPrice();
	    }

	    cart.setProductInCart(productInCart);
	    cart.setTotalPrice(totalCost);
	    cart.setTotalQuantity(productInCart.size());

	    // Save the cart
	    Cart savedCart = cartRepository.save(cart);

	    // Update the user's cart
	    user.setCart(savedCart);
	    customerRepository.save(user);

	    return savedCart;
	}

	@Override
	public Cart updateCart(CartDTO cartDTO) {
		Cart cart = null;
		Optional<Cart> optcard = cartRepository.findById(cartDTO.getId());

		if (!optcard.isPresent()) {
			throw new CustomException("cart Not Found!!");
		}
		Optional<Customer> optCustomer = customerRepository.findById(cartDTO.getUserid());
		if (!optCustomer.isPresent()) {
			throw new CustomException("User Not Found!!");
		}

		Customer user = optCustomer.get();
		Set<Product> productInCart = null;
		if (user.getCart() == null) {
			cart = new Cart();
			productInCart = new HashSet<Product>();
		} else {
			cart = user.getCart();
			productInCart = cart.getProductInCart();
		}

		List<Integer> productIds = cartDTO.getProductId();
		for (Integer productId : productIds) {
			Product dbproduct = productRepository.findByProductIdAndIsActiveTrue(productId);
			if (dbproduct == null) {
				throw new CustomException("Product Not Found!!");
			}
			productInCart.add(dbproduct);
		}
		cart.setProductInCart(productInCart);
		double totalcost = 0.0;
		for (Product product : productInCart) {
			totalcost = totalcost + product.getProductPrice();
		}

		cart.setTotalPrice(totalcost);
		cart.setTotalQuantity(productInCart.size());
		Cart savecart = cartRepository.save(cart);
		user.setCart(savecart);
		customerRepository.save(user);
		return cart;
	}

	@Override
	public Cart deleteProductFromCart(int productId, int cartId) {
		Cart cart = null;
		Optional<Cart> optcard = cartRepository.findById(cartId);

		if (!optcard.isPresent()) {
			throw new CustomException("cart Not Found!!");
		}
		cart = optcard.get();
		Set<Product> productInCart = cart.getProductInCart();

		Product dbproduct = productRepository.findByProductIdAndIsActiveTrue(productId);
		if (dbproduct == null) {
			throw new CustomException("Product Not Found!!");
		}
		productInCart.remove(dbproduct);
		cart.setProductInCart(productInCart);
		double totalPrice = cart.getTotalPrice();
		cart.setTotalPrice(totalPrice - dbproduct.getProductPrice());
		cart.setTotalQuantity(productInCart.size());
		cartRepository.save(cart);
		return cart;
	}

}
