package com.proa.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.proa.app.entities.Order;

public interface IOrderDAO extends CrudRepository<Order, Long> {

}
