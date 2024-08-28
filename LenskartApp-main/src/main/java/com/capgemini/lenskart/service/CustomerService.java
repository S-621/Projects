package com.capgemini.lenskart.service;

import com.capgemini.lenskart.dto.CustomerDTO;
import com.capgemini.lenskart.dto.SignInDto;
import com.capgemini.lenskart.entity.Cart;
import com.capgemini.lenskart.response.CommonResponse;

public interface CustomerService {

	public CustomerDTO registerCustomer(CustomerDTO customerDTO);

	public CustomerDTO updateCustomer(CustomerDTO customerDTO);

	public boolean deleteCustomer(CustomerDTO customerDTO);

	public CustomerDTO getByEmail(String email);

	public Cart getCartByCustomer(int customerId);
	
	public CommonResponse signIn(SignInDto signRequestDTO);
	
	public CustomerDTO updateCustomerById(Integer userId,CustomerDTO customerDTO);
	
	public String deleteCustomerById(Integer userId);
	}
