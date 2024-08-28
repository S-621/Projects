package com.javatechie.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.entity.UserCredential;
import com.javatechie.exception.CustomerNotFoundException;
import com.javatechie.repository.UserCredentialRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserCredentialRepository userRepo;

	@Override
	public String deleteCustomer(int id) {
		try {
			userRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException());
			userRepo.deleteById(id);
		} catch (CustomerNotFoundException e) {
			System.out.println(e);
			return "invalid Customer Id";
		}
		return "Customer Deleted Successfully";
	}

	@Override
	public String updateCustomer(int id, UserCredential user) {

		UserCredential users;
		try {
			users = userRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException());

			if (user.getUsername() != null)
				users.setUsername(user.getUsername());

			if (user.getNumber() != 0)
				users.setNumber(user.getNumber());

			if (user.getAddress() != null)
				users.setAddress(user.getAddress());

			if (user.getEmail() != null)
				users.setEmail(user.getEmail());

			userRepo.save(user);
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return "Invalid customer.Customer data not updated";

		}

		return "Customer Updated Successfully";

	}

	@Override
	public UserCredential getCustomerById(int id) throws CustomerNotFoundException {
		UserCredential user = userRepo.findById(id).get();
		if (user == null) {
			throw new CustomerNotFoundException("Invalid customer ID");
		}
		return user;
	}

	@Override
	public UserCredential getCustomerByEmail(String email) throws CustomerNotFoundException {
		UserCredential user = userRepo.findByEmail(email).get();
		if (user == null) {
			throw new CustomerNotFoundException("Invalid customer email");
		}
		return user;
	}

	@Override
	public List<UserCredential> getAllByRole(String role) {
		List<UserCredential> users = userRepo.findByRole(role); // Assuming findAll() returns all users

		
		// Filter users based on the role
//		List<UserCredential> usersWithRole = users.stream().filter(user -> user.getRole().equals(role)) // Assuming
																										// UserCredential
																										// has a method
																										// getRole()
//				.collect(Collectors.toList());

		return users;
	}
}
