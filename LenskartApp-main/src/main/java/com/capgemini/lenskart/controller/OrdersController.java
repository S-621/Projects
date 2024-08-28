package com.capgemini.lenskart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.lenskart.dto.OrdersDTO;
import com.capgemini.lenskart.entity.Orders;
import com.capgemini.lenskart.service.OrdersService;

@RestController
@RequestMapping("/order")
public class OrdersController {

	@Autowired
	private OrdersService orderService;

	@PostMapping("/addOrders")
	public ResponseEntity<Orders> addOrders(@RequestBody OrdersDTO ordersDTO) {

		return ResponseEntity.ok(orderService.addOrders(ordersDTO));
	}

	@DeleteMapping("/deleteOrders")
	public ResponseEntity<Boolean> deleteOrders(@RequestBody OrdersDTO ordersDTO) {

		return ResponseEntity.ok(orderService.deleteOrders(ordersDTO));
	}

	@PutMapping("/updateOrders")
	public ResponseEntity<Orders> updateOrders(@RequestBody OrdersDTO ordersDTO) {

		return ResponseEntity.ok(orderService.updateOrders(ordersDTO));
	}

	@GetMapping("/getById")
	public ResponseEntity<Orders> getById(@RequestParam int orderId) {

		return ResponseEntity.ok(orderService.getById(orderId));
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Orders>> findAll() {
		return ResponseEntity.ok(orderService.findAll());
	}

	@GetMapping("/getOrderCustomerId")
	public ResponseEntity<List<Orders>> getOrderCustomerId(@RequestParam int customerId) {

		return ResponseEntity.ok(orderService.getOrderCustomerId(customerId));
	}

}
