package com.javatechie.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.javatechie.controller.AuthController;
import com.javatechie.dto.AuthRequest;
import com.javatechie.dto.LoginResponse;
import com.javatechie.entity.UserCredential;
import com.javatechie.exception.CustomerNotFoundException;
import com.javatechie.service.AuthService;
import com.javatechie.service.JwtService;
import com.javatechie.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void getToken_ValidCredentials_ReturnsToken() throws CustomerNotFoundException, AuthenticationException {
        // Arrange
        AuthRequest authRequest = new AuthRequest("test@example.com", "password");
        UserCredential user = new UserCredential();
        user.setEmail("test@example.com");
        when(userService.getCustomerByEmail(authRequest.getEmail())).thenReturn(user);
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(authService.generateToken(authRequest.getEmail())).thenReturn("test_token");

        // Act
        LoginResponse response = authController.getToken(authRequest);

        // Assert
        assertEquals("test_token", response.getToken());
        assertEquals(user, response.getUser());
    }

//    @Test
//    public void getToken_InvalidCredentials_ThrowsException() throws CustomerNotFoundException, AuthenticationException {
//        // Arrange
//        AuthRequest authRequest = new AuthRequest("test@example.com", "password");
//        when(authenticationManager.authenticate(any())).thenThrow(AuthenticationException.class);
//
//        // Act & Assert
//        assertThrows(RuntimeException.class, () -> {
//            authController.getToken(authRequest);
//        });
//    }

    @Test
    public void getAllByRole_ValidRole_ReturnsUsers() {
        // Arrange
        String role = "ROLE_ADMIN";
        UserCredential user = new UserCredential();
        user.setRole(role);
        List<UserCredential> users = Collections.singletonList(user);
        when(userService.getAllByRole(role)).thenReturn(users);

        // Act
        ResponseEntity<List<UserCredential>> response = authController.getAllByRole(role);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }
    
    @Test
    public void addNewUser_ValidUser_ReturnsSuccessMessage() throws CustomerNotFoundException {
        // Arrange
        UserCredential user = new UserCredential();
        when(authService.saveUser(user)).thenReturn("User saved successfully");

        // Act
        String response = authController.addNewUser(user);

        // Assert
        assertEquals("User saved successfully", response);
    }

    @Test
    public void deleteUser_ValidId_ReturnsSuccessResponse() {
        // Arrange
        int userId = 1;
        when(userService.deleteCustomer(userId)).thenReturn("User deleted successfully");

        // Act
        ResponseEntity<String> response = authController.deleteUser(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User deleted successfully", response.getBody());
    }

    @Test
    public void updateUser_ValidIdAndUser_ReturnsSuccessResponse() {
        // Arrange
        int userId = 1;
        UserCredential user = new UserCredential();
        when(userService.updateCustomer(userId, user)).thenReturn("User updated successfully");

        // Act
        ResponseEntity<String> response = authController.updateUser(userId, user);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User updated successfully", response.getBody());
    }

    @Test
    public void fetchById_ValidId_ReturnsUser() throws CustomerNotFoundException {
        // Arrange
        int userId = 1;
        UserCredential user = new UserCredential();
        when(userService.getCustomerById(userId)).thenReturn(user);

        // Act
        ResponseEntity<UserCredential> response = authController.fetchById(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void fetchByEmail_ValidEmail_ReturnsUser() throws CustomerNotFoundException {
        // Arrange
        String email = "test@example.com";
        UserCredential user = new UserCredential();
        when(userService.getCustomerByEmail(email)).thenReturn(user);

        // Act
        ResponseEntity<UserCredential> response = authController.fetchByEmail(email);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }
}
