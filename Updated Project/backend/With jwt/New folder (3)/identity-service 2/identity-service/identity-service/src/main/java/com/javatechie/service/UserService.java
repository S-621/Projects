package com.javatechie.service;

import java.util.List;

import com.javatechie.entity.UserCredential;
import com.javatechie.exception.CustomerNotFoundException;

public interface UserService {
	public String deleteCustomer(int id);
	public String updateCustomer(int id, UserCredential user);
	public UserCredential getCustomerById(int id) throws CustomerNotFoundException;
	public UserCredential getCustomerByEmail(String email) throws CustomerNotFoundException;
	public List<UserCredential> getAllByRole(String role) throws CustomerNotFoundException;
}
