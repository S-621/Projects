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

import com.capgemini.lenskart.dto.CustomerDTO;
import com.capgemini.lenskart.dto.UserDTO;
import com.capgemini.lenskart.entity.Cart;
import com.capgemini.lenskart.exception.CustomException;
import com.capgemini.lenskart.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	//method to register a new customer
	@PostMapping("/v1/registerCustomer")
	public ResponseEntity<UserDTO> registerCustomer(@Validated @RequestBody CustomerDTO userDTO)throws CustomException {
		return ResponseEntity.ok(customerService.registerCustomer(userDTO));
	}
	//method to update info of existing customer
	@PutMapping("/v1/updateCustomer")
	public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO userDTO) throws CustomException {
		return ResponseEntity.ok(customerService.updateCustomer(userDTO));
	}
	//method to fetch customer by email
	@GetMapping("/v1/getByEmail")
	public ResponseEntity<UserDTO> getByEmail(@RequestParam String email)throws CustomException {
		return ResponseEntity.ok(customerService.getByEmail(email));
	}
	//method to delete customer
	@DeleteMapping("/v1/deleteCustomer")
	public ResponseEntity<Boolean> deleteCustomer(@Validated @RequestBody CustomerDTO userDTO) {
		return ResponseEntity.ok(customerService.deleteCustomer(userDTO));
	}
	//method to fetch cart of a customer
	@GetMapping("/v1/getCartByCustomer")
	public ResponseEntity<Cart> getCartByCustomer(@RequestParam int customerId)throws CustomException {
		return ResponseEntity.ok(customerService.getCartByCustomer(customerId));
	}
	//method to update customer by providing customer id
	@PutMapping("/v1/updateCustomerById/{userId}")
	public ResponseEntity<CustomerDTO> updateById(@PathVariable("userId")Integer userId, @RequestBody CustomerDTO customerDTO)
	throws CustomException{
		return ResponseEntity.ok(customerService.updateCustomerById(userId, customerDTO));
	}
	//method to delete customer by providing customer id
	@DeleteMapping("/v1/deleteCustomerById/{userId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable("userId")Integer userId)throws CustomException{
		return ResponseEntity.ok(customerService.deleteCustomerById(userId));
	}
}
