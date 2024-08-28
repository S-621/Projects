package com.capgemini.lenskart.entity;

import jakarta.persistence.Entity;

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
@Table(name = "_admin")
public class Admin extends User {
	private String firstName;
	private String lastName;
	private String role = "Admin";
	private Long number;
	private String address;
}
