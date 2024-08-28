package com.capgemini.lenskart.entity;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "_customer")
public class Customer extends User {

	private String firstName;
	private String lastName;
	private String role = "Customer";
	private Long number;
	private String address;

	@OneToMany
	private List<Orders> userOrders = new ArrayList<>();

	@OneToOne
	private Cart cart;

}
