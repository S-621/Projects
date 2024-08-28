package com.capgemini.lenskart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capgemini.lenskart.dto.OrdersDTO;
import com.capgemini.lenskart.entity.Customer;
import com.capgemini.lenskart.entity.Orders;
import com.capgemini.lenskart.repository.CustomerRepository;
import com.capgemini.lenskart.repository.OrdersRepository;
import com.capgemini.lenskart.repository.PaymentRepository;
import com.capgemini.lenskart.repository.ProductRepository;
import com.capgemini.lenskart.service.impl.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

	@Mock
	private OrdersRepository ordersRepository;

	@Mock
	private PaymentRepository paymentRepository;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private OrderServiceImpl orderService;

//    @Test
//    void testAddOrders() {
//        // Mocking data
//        OrdersDTO ordersDTO = new OrdersDTO();
//        ordersDTO.setCart(new CartDTO());
//        ordersDTO.setPayment(new Payment());
//
//        User user = new User();
//        user.setUserid((int) 1L);
//
//        Product product1 = new Product();
//        product1.setProductId(1);
//
//        Product product2 = new Product();
//        product2.setProductId(2);
//
//        ordersDTO.getCart().setUserid(1);
//        ordersDTO.getCart().setProductId(Arrays.asList(1, 2));
//
//        when(customerRepository.findById((int) 1L)).thenReturn(Optional.of(user));
//        when(productRepository.findByProductIdAndIsActiveTrue(1)).thenReturn(product1);
//        when(productRepository.findByProductIdAndIsActiveTrue(2)).thenReturn(product2);
//        when(paymentRepository.save(any())).thenReturn(new Payment());
//        when(ordersRepository.save(any())).thenReturn(new Orders());
//
//        // Test the method
//        Orders resultOrders = orderService.addOrders(ordersDTO);
//
//        // Assertions
//        assertNotNull(resultOrders);
//    }

	@Test
	void testUpdateOrders() {
		// Similar to addOrders, mock the necessary data and test the updateOrders
		// method
	}

	@Test
	void testDeleteOrders() {
		// Mocking data
		OrdersDTO ordersDTO = new OrdersDTO();
		ordersDTO.setOrderId(1);

		Orders orders = new Orders();
		orders.setOrderId(1);

		when(ordersRepository.findById(1)).thenReturn(Optional.of(orders));
		when(ordersRepository.save(any())).thenReturn(new Orders());

		// Test the method
		boolean result = orderService.deleteOrders(ordersDTO);

		// Assertions
		assertTrue(result);
	}

	@Test
	void testGetById() {
		// Mocking data
		Orders orders = new Orders();
		orders.setOrderId(1);

		when(ordersRepository.findById(1)).thenReturn(Optional.of(orders));

		// Test the method
		Orders resultOrders = orderService.getById(1);

		// Assertions
		assertNotNull(resultOrders);
		assertEquals(1, resultOrders.getOrderId());
	}

	@Test
	void testFindAll() {
		// Mocking data
		List<Orders> ordersList = Arrays.asList(new Orders(), new Orders());

		when(ordersRepository.findAll()).thenReturn(ordersList);

		// Test the method
		List<Orders> resultOrdersList = orderService.findAll();

		// Assertions
		assertNotNull(resultOrdersList);
		assertEquals(ordersList.size(), resultOrdersList.size());
	}

	@Test
	void testGetOrderCustomerId() {
		// Mocking data
		Customer user = new Customer();
		user.setUserid((int) 1L);
		Orders orders = new Orders();
		orders.setOrderId(1);

		user.setUserOrders(Arrays.asList(orders));

		when(customerRepository.findById(1)).thenReturn(Optional.of(user));

		// Test the method
		List<Orders> resultOrdersList = orderService.getOrderCustomerId(1);

		// Assertions
		assertNotNull(resultOrdersList);
		assertEquals(1, resultOrdersList.size());
	}
}