package com.javatechie.controller;

import com.javatechie.dto.AuthRequest;
import com.javatechie.dto.LoginResponse;
import com.javatechie.entity.UserCredential;
import com.javatechie.exception.CustomerNotFoundException;
import com.javatechie.service.AuthService;
import com.javatechie.service.JwtService;
import com.javatechie.service.UserServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService service;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/register")
	public String addNewUser(@RequestBody UserCredential user) throws CustomerNotFoundException {
			return service.saveUser(user);
	}

	@PostMapping("/token")
	public LoginResponse getToken(@Validated @RequestBody AuthRequest authRequest) throws CustomerNotFoundException {
		LoginResponse res = new LoginResponse();
		UserCredential user = userService.getCustomerByEmail(authRequest.getEmail());
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		if (authenticate.isAuthenticated()) {
			res.setToken(service.generateToken(authRequest.getEmail()));
			res.setUser(user);
			return res;
		} else {
			throw new RuntimeException("invalid access");
		}
	}

//	@GetMapping("/validate")
//	public String validateToken(@RequestParam("token") String token) {
//		service.validateToken(token);
//		return "Token is valid";
//	}

	@PostMapping("/authenticate")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws CustomerNotFoundException {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getEmail());
		} else {
			throw new CustomerNotFoundException("invalid user request !");
		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		return new ResponseEntity<String>(userService.deleteCustomer(id), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody UserCredential user) {
		return new ResponseEntity<String>(userService.updateCustomer(id, user), HttpStatus.OK);
	}

	@GetMapping("/fetchById/{id}")
	public ResponseEntity<UserCredential> fetchById(@PathVariable("id") int id) throws CustomerNotFoundException {
		return new ResponseEntity<UserCredential>(userService.getCustomerById(id), HttpStatus.OK);
	}

//	@GetMapping("/fetchByRole/{role}")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//	public ResponseEntity<UserCredential> fetchByRole(@PathVariable("role") String role)
//			throws CustomerNotFoundException {
//		return new ResponseEntity<UserCredential>(userService.getAllByRole(role), HttpStatus.OK);
//	}

	@GetMapping("/fetchByRole/{role}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<UserCredential>> getAllByRole(@PathVariable("role") String role) {
		return new ResponseEntity<List<UserCredential>>(userService.getAllByRole(role), HttpStatus.OK);
	}

	@GetMapping("/fetchByEmail/{email}")
	public ResponseEntity<UserCredential> fetchByEmail(@PathVariable("email") String email)
			throws CustomerNotFoundException {
		return new ResponseEntity<UserCredential>(userService.getCustomerByEmail(email), HttpStatus.OK);
	}
}
