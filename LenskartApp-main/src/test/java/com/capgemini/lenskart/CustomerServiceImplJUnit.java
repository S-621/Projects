package com.capgemini.lenskart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.capgemini.lenskart.dto.CustomerDTO;
import com.capgemini.lenskart.dto.UserDTO;
import com.capgemini.lenskart.entity.Customer;
import com.capgemini.lenskart.entity.User;
import com.capgemini.lenskart.repository.CustomerRepository;
import com.capgemini.lenskart.service.impl.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Test
	void testRegisterCustomer() {
		// Mocking data
		CustomerDTO userDTO = new CustomerDTO();
		userDTO.setEmail("test@example.com");

		Customer dbUser = new Customer();
		dbUser.setEmail("test@example.com");

		when(customerRepository.findByEmail("test@example.com")).thenReturn(null);
		when(modelMapper.map(userDTO, User.class)).thenReturn(dbUser);
		when(customerRepository.save(dbUser)).thenReturn(dbUser);

		// Test the method
		CustomerDTO resultUserDTO = customerService.registerCustomer(userDTO);

		// Assertions
		assertNotNull(resultUserDTO);
		assertEquals("test@example.com", resultUserDTO.getEmail());
	}

	@Test
	void testUpdateCustomer() {
		// Similar to registerCustomer, mock the necessary data and test the
		// updateCustomer method
	}

	@Test
	void testDeleteCustomer() {
		// Mocking data
		CustomerDTO userDTO = new CustomerDTO();
		userDTO.setEmail("test@example.com");

		Customer dbUser = new Customer();
		dbUser.setEmail("test@example.com");

		when(customerRepository.findByEmail("test@example.com")).thenReturn(dbUser);
		when(customerRepository.save(dbUser)).thenReturn(dbUser);

		// Test the method
		boolean result = customerService.deleteCustomer(userDTO);

		// Assertions
		assertTrue(result);
	}

	@Test
	void testGetByEmail() {
		// Mocking data
		String email = "test@example.com";
		CustomerDTO expectedUserDTO = new CustomerDTO();
		expectedUserDTO.setEmail(email);

		Customer dbUser = new Customer();
		dbUser.setEmail(email);
		dbUser.setIsActive(true);

		when(customerRepository.findByEmail(email)).thenReturn(dbUser);
		when(modelMapper.map(dbUser, UserDTO.class)).thenReturn(expectedUserDTO);
		when(customerRepository.save(dbUser)).thenReturn(dbUser);

		// Test the method
		CustomerDTO resultUserDTO = customerService.getByEmail(email);

		// Assertions
		assertNotNull(resultUserDTO);
		assertEquals(email, resultUserDTO.getEmail());
	}
}
