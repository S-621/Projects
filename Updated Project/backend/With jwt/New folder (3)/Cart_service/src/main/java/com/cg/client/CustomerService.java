package com.cg.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.dto.CustomerDTO;
import com.cg.exception.CustomerNotFoundException;

@FeignClient(name = "Identity-Service", url = "http://localhost:8093")
public interface CustomerService {
	@GetMapping("/auth/fetchById/{id}")
	public CustomerDTO getCustomerById(@PathVariable(value = "id") int id) throws CustomerNotFoundException;
}
