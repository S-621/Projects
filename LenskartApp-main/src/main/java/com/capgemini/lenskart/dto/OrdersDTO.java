package com.capgemini.lenskart.dto;

import com.capgemini.lenskart.entity.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

	private int orderId;
	private CartDTO cart;
	private Payment payment;

}
