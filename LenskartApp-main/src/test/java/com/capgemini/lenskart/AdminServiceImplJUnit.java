package com.capgemini.lenskart;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capgemini.lenskart.dto.AdminDTO;
import com.capgemini.lenskart.entity.Admin;
import com.capgemini.lenskart.exception.CustomException;
import com.capgemini.lenskart.repository.AdminRepository;
import com.capgemini.lenskart.service.impl.AdminServiceImpl;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

	@Mock
	private AdminRepository adminRepository;

	@InjectMocks
	private AdminServiceImpl adminService;

	@Test
	void testAddAdminUserAlreadyExists() {
		// Mocking data
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setEmail("admin@example.com");

		Admin dbUser = new Admin();

		when(adminRepository.findByEmail("admin@example.com")).thenReturn(dbUser);

		// Test the method
		assertThrows(CustomException.class, () -> adminService.addAdmin(adminDTO),
				"Exception should be thrown for existing user");
	}

	@Test
	void testUpdateAdminUserNotFound() {
		// Mocking data
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setEmail("admin@example.com");

		Admin dbUser = null;

		when(adminRepository.findByEmail("admin@example.com")).thenReturn(dbUser);

		// Test the method
		assertThrows(CustomException.class, () -> adminService.updateAdmin(adminDTO),
				"Exception should be thrown for non-existing user");
	}

	@Test
	void testGetAdminByEmailUserNotFound() {
		// Mocking data
		String email = "admin@example.com";

		Admin dbUser = null;

		when(adminRepository.findByEmail("admin@example.com")).thenReturn(dbUser);

		// Test the method
		assertThrows(CustomException.class, () -> adminService.getAdminByEmail(email),
				"Exception should be thrown for non-existing user");
	}
}
