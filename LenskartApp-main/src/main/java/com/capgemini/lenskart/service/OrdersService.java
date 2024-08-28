package com.capgemini.lenskart.service;

import java.util.List;

import com.capgemini.lenskart.dto.OrdersDTO;
import com.capgemini.lenskart.entity.Orders;

public interface OrdersService {

	public Orders addOrders(OrdersDTO ordersDTO);

	public Orders updateOrders(OrdersDTO ordersDTO);

	public boolean deleteOrders(OrdersDTO ordersDTO);

	public Orders getById(int id);

	public List<Orders> findAll();

	public List<Orders> getOrderCustomerId(int customerId);
}
