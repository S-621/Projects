package com.capgemini.lenskart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.lenskart.dto.SignInDto;
import com.capgemini.lenskart.response.CommonResponse;
import com.capgemini.lenskart.service.AdminService;
import com.capgemini.lenskart.service.CustomerService;


@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	AdminService adminService;

	@PostMapping("customer")
	public ResponseEntity<CommonResponse> signIn(@RequestBody SignInDto signInDto){
		CommonResponse signInResponseDto = customerService.signIn(signInDto);
		return ResponseEntity.ok(signInResponseDto);
	}
	@PostMapping("admin")
	public ResponseEntity<CommonResponse> signInAdmin(@RequestBody SignInDto signInDto){
		CommonResponse response = adminService.signIn(signInDto);
		return ResponseEntity.ok(response);
	}
}