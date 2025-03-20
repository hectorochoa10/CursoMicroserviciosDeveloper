package com.proa.app.services;

import java.util.List;

import com.proa.app.entities.Order;
import com.proa.app.exceptions.ClientNotFoundException;
import com.proa.app.exceptions.OrderNotFoundException;

public interface IService {
	boolean insert(long idClient, double total)throws ClientNotFoundException;
	List<Order> selectAll();
	boolean delete(long id)throws OrderNotFoundException;	
}
