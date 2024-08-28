package com.capgemini.lenskart.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lenskart.dto.CartDTO;
import com.capgemini.lenskart.dto.OrdersDTO;
import com.capgemini.lenskart.entity.Customer;
import com.capgemini.lenskart.entity.Orders;
import com.capgemini.lenskart.entity.Payment;
import com.capgemini.lenskart.entity.Product;
import com.capgemini.lenskart.exception.CustomException;
import com.capgemini.lenskart.repository.CustomerRepository;
import com.capgemini.lenskart.repository.OrdersRepository;
import com.capgemini.lenskart.repository.PaymentRepository;
import com.capgemini.lenskart.repository.ProductRepository;
import com.capgemini.lenskart.service.OrdersService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrdersService {

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional
	public Orders addOrders(OrdersDTO ordersDTO) {
	    Orders orders = new Orders();

	    Optional<Customer> optCustomer = customerRepository.findById(ordersDTO.getCart().getUserid());
	    if (!optCustomer.isPresent()) {
	        throw new CustomException("User Not Found!!");
	    }
	    Customer user = optCustomer.get();

	    orders.setDate(LocalDateTime.now());

	    Set<Product> productInCart = new HashSet<>();
	    CartDTO cartDTO = ordersDTO.getCart();
	    List<Integer> productIds = cartDTO.getProductId();

	    double totalCost = 0.0;
	    int totalQuantity = 0;

	    for (Integer productId : productIds) {
	        Product dbProduct = productRepository.findByProductIdAndIsActiveTrue(productId);
	        if (dbProduct == null) {
	            throw new CustomException("Product Not Found!!");
	        }
	        productInCart.add(dbProduct);

	        // Update total cost and quantity for each product added
	        totalCost += dbProduct.getProductPrice();
	        totalQuantity += 1; // Assuming each product has quantity 1, adjust if needed
	    }

	    // Save the payment
	    Payment savePayment = paymentRepository.save(ordersDTO.getPayment());
	    orders.setPayment(savePayment);

	    // Set the products, customer, and other details
	    orders.setProducts(productInCart);
	    orders.setCustomer(user);
	    orders.setOrderStatus("ORDER PLACED");
	    orders.setTotalQuantity(totalQuantity);
	    orders.setTotalPrice(totalCost);

	    // Save the orders
	    Orders saveOrders = ordersRepository.save(orders);

	    // Update user orders
	    List<Orders> userOrders = user.getUserOrders();
	    userOrders.add(saveOrders);
	    user.setUserOrders(userOrders);
	    customerRepository.save(user);

	    return saveOrders;
	}

	@Override
	public Orders updateOrders(OrdersDTO ordersDTO) {
		Orders orders = null;

		Optional<Orders> optOrders = ordersRepository.findById(ordersDTO.getOrderId());
		if (!optOrders.isPresent()) {
			throw new CustomException("order Not Found!!");
		}
		orders = optOrders.get();

		Optional<Customer> optCustomer = customerRepository.findById(ordersDTO.getCart().getUserid());
		if (!optCustomer.isPresent()) {
			throw new CustomException("User Not Found!!");
		}
		Customer user = optCustomer.get();

		orders.setDate(LocalDateTime.now());
		Set<Product> productInCart = null;
		CartDTO cartDTO = ordersDTO.getCart();
		productInCart = new HashSet<Product>();
		List<Integer> productIds = cartDTO.getProductId();
		for (Integer productId : productIds) {
			Product dbproduct = productRepository.findByProductIdAndIsActiveTrue(productId);
			if (dbproduct == null) {
				throw new CustomException("Product Not Found!!");
			}
			productInCart.add(dbproduct);
		}

		Payment savePayment = paymentRepository.save(ordersDTO.getPayment());
		orders.setPayment(savePayment);
		orders.setProducts(productInCart);
		orders.setCustomer(user);
		orders.setOrderStatus("ORDER PLACED");
		Orders saveOrders = ordersRepository.save(orders);
		List<Orders> userOrders = user.getUserOrders();
		userOrders.add(saveOrders);
		user.setUserOrders(userOrders);
		customerRepository.save(user);
		return saveOrders;
	}

	@Override
	public boolean deleteOrders(OrdersDTO ordersDTO) {
		Orders orders = null;

		Optional<Orders> optOrders = ordersRepository.findById(ordersDTO.getOrderId());
		if (!optOrders.isPresent()) {
			throw new CustomException("order Not Found!!");
		}
		orders = optOrders.get();
		orders.setOrderStatus("ORDER CANCELLED");
		ordersRepository.save(orders);
		return true;
	}

	@Override
	public Orders getById(int id) {

		Optional<Orders> optOrders = ordersRepository.findById(id);
		if (!optOrders.isPresent()) {
			throw new CustomException("order Not Found!!");
		}
		Orders orders = optOrders.get();
		return orders;
	}

	@Override
	public List<Orders> findAll() {
		List<Orders> findAllOrders = ordersRepository.findAll();
		return findAllOrders;
	}

	@Override
	public List<Orders> getOrderCustomerId(int customerId) {
		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if (!optCustomer.isPresent()) {
			throw new CustomException("User Not Found!!");
		}
		Customer user = optCustomer.get();
		return user.getUserOrders();
	}

}
