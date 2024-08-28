package com.capgemini.lenskart.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

	private int id;
	private List<Integer> productId;
	private int userid;
	private int totalQuantity;
	private double totalPrice;

}