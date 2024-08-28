package com.capgemini.lenskart.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lenskart.dto.CustomerDTO;
import com.capgemini.lenskart.dto.SignInDto;
import com.capgemini.lenskart.entity.Cart;
import com.capgemini.lenskart.entity.Customer;
import com.capgemini.lenskart.entity.User;
import com.capgemini.lenskart.exception.CustomException;
import com.capgemini.lenskart.repository.CustomerRepository;
import com.capgemini.lenskart.response.CommonResponse;
import com.capgemini.lenskart.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CustomerDTO registerCustomer(CustomerDTO customerDTO) throws CustomException {
		Customer user = new Customer();

		Customer dbUser = customerRepository.findByEmail(customerDTO.getEmail());
		if (dbUser != null) {
			throw new CustomException("User already Found!!");
		}
		user = modelMapper.map(customerDTO, Customer.class);
		user.setRole("Customer");
		User saveUser = customerRepository.save(user);
		customerDTO.setUserid(saveUser.getUserid());
		return customerDTO;
	}

	@Override
	public CustomerDTO updateCustomer(CustomerDTO customerDTO) throws CustomException {
		Customer dbUser = customerRepository.findByEmail(customerDTO.getEmail());
		if (dbUser == null) {
			throw new CustomException("User Not Found!!");
		}
		dbUser = modelMapper.map(customerDTO, Customer.class);
		customerRepository.save(dbUser);
		return customerDTO;
	}

	@Override
	public boolean deleteCustomer(CustomerDTO customerDTO){
		Customer dbUser = customerRepository.findByEmail(customerDTO.getEmail());
		dbUser.setIsActive(false);
		customerRepository.save(dbUser);
		return true;
	}

	@Override
	public CustomerDTO getByEmail(String email) throws CustomException {
		CustomerDTO userDTO = new CustomerDTO();
		Customer dbUser = customerRepository.findByEmail(email);
		if (dbUser == null) {
			throw new CustomException("No such user");
		}
		if (dbUser != null) {
			Boolean isActive = dbUser.getIsActive();
			if (isActive == false) {
				throw new CustomException("User is Not active!!");
			}
		}

		userDTO = modelMapper.map(dbUser, CustomerDTO.class);
		customerRepository.save(dbUser);
		return userDTO;
	}

	@Override
	public Cart getCartByCustomer(int customerId) throws CustomException {
		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if (!optCustomer.isPresent()) {
			throw new CustomException("No Customer cart");
		}
		Customer user = optCustomer.get();
		return user.getCart();
	}

	@Override
	public CommonResponse signIn(SignInDto signRequestDTO) {
		Customer user = customerRepository.findByEmail(signRequestDTO.getEmail());
		if (user != null) {
			String passwrd = signRequestDTO.getPassword();
			String userPasswrd = user.getPassword();
			boolean flag = passwrd.matches(userPasswrd);
			if (flag) {
				return new CommonResponse("Login Successful");
			} else {
				return new CommonResponse("password incorrect");
			}
		} else {
			return new CommonResponse("No found");
		}
	}

	@Override
	public CustomerDTO updateCustomerById(Integer userId, CustomerDTO customerDTO)throws CustomException {

		Optional<Customer> cust = customerRepository.findById(userId);
		if (cust.isPresent()) {
			Customer customer = cust.get();
			customer.setFirstName(customerDTO.getFirstName());
			customer.setLastName(customerDTO.getLastName());
			customer.setNumber(customerDTO.getNumber());
			customer.setAddress(customerDTO.getAddress());
			customer.setUserName(customerDTO.getUserName());
			customer.setPassword(customerDTO.getPassword());
			customer.setEmail(customerDTO.getEmail());
			customer.setRole("Customer");
			customerRepository.save(customer);
		}else {
			throw new CustomException("Invalid userId");
		}

		return customerDTO;
	}

	@Override
	public String deleteCustomerById(Integer userId)throws CustomException {
		if(customerRepository.existsById(userId)) {
			customerRepository.deleteById(userId);
			return "Deleted Successfully";
		}else {
			throw new CustomException("Invalid ID");
		}
	}
}
